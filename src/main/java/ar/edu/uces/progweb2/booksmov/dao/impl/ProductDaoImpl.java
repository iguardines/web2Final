package ar.edu.uces.progweb2.booksmov.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.booksmov.dao.ProductDao;
import ar.edu.uces.progweb2.booksmov.dto.CriteriaSearchDto;
import ar.edu.uces.progweb2.booksmov.dto.FilterDto;
import ar.edu.uces.progweb2.booksmov.dto.PaginationDetailsDto;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.model.Movie;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.model.SearchResult;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResult getProductsByUserId(Long id, FilterDto filterDto, CriteriaSearchDto cs) {

		SearchResult searchResult = new SearchResult();
		int pageSize = 10;
		int firstResult = cs.getPage() * pageSize;
		
		Criteria criteriaCount = formCriteria(filterDto);
		Projection projection = Projections.rowCount();
		criteriaCount.setProjection(projection);
		criteriaCount.add(Restrictions.eq("user.id", id));
		Long countResults = (Long) criteriaCount.uniqueResult();
		int lastPageNumber = (int) ((countResults == pageSize || countResults % pageSize == 0) ? countResults / pageSize : (countResults / pageSize) + 1);
		
		Criteria criteriaSelect = formCriteria(filterDto);
		criteriaSelect.add(Restrictions.eq("user.id", id));
		criteriaSelect.addOrder(cs.getOrder().equalsIgnoreCase("asc") ? Order.asc(cs.getPropertyForOrder()) : Order.desc(cs.getPropertyForOrder()));
		criteriaSelect.setFirstResult(firstResult);
		criteriaSelect.setMaxResults(pageSize);
		List<Product> products = (List<Product>) criteriaSelect.list();
		
		PaginationDetailsDto paginationDetails = new PaginationDetailsDto();
	    paginationDetails.setCurrentPage(cs.getPage());
	    paginationDetails.setItemsPerPage(pageSize);
	    paginationDetails.setMaxPage(lastPageNumber);
	    paginationDetails.setTotalResults(countResults.intValue());
	    paginationDetails.setBegin(1);
	    paginationDetails.setEnd(lastPageNumber);
	    
	    searchResult.setProducts(products);
	    searchResult.setPaginationDetails(paginationDetails);
	    
		return searchResult;
	}

	@Override
	public Product getProductById(Long id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchResult getProductsByCriteria(FilterDto filterDto, CriteriaSearchDto cs) {
		
		SearchResult searchResult = new SearchResult();
		int pageSize = 10;
		int firstResult = cs.getPage() * pageSize;
		
		Criteria criteriaCount = formCriteria(filterDto);
		Projection projection = Projections.countDistinct("id");
		criteriaCount.setProjection(projection);
		Long countResults = (Long) criteriaCount.uniqueResult();
		int lastPageNumber = (int) ((countResults == pageSize || countResults % pageSize == 0) ? countResults / pageSize : (countResults / pageSize) + 1);
		
		Criteria criteriaSelect = formCriteria(filterDto);
		criteriaSelect.addOrder(cs.getOrder().equalsIgnoreCase("asc") ? Order.asc(cs.getPropertyForOrder()) : Order.desc(cs.getPropertyForOrder()));
		criteriaSelect.setFirstResult(firstResult);
		criteriaSelect.setMaxResults(pageSize);
		List<Product> products = (List<Product>) criteriaSelect.list();
		
		PaginationDetailsDto paginationDetails = new PaginationDetailsDto();
	    paginationDetails.setCurrentPage(cs.getPage());
	    paginationDetails.setItemsPerPage(Integer.valueOf(pageSize));
	    paginationDetails.setMaxPage(Integer.valueOf(lastPageNumber));
	    paginationDetails.setTotalResults(countResults.intValue());
	    paginationDetails.setBegin(1);
	    paginationDetails.setEnd( lastPageNumber );
		
		searchResult.setProducts(products);
		searchResult.setPaginationDetails(paginationDetails);
		return searchResult;
		
	}
	
	private Criteria formCriteria(FilterDto filterDto) {
		Criteria criteria = getCriteriaForType(filterDto.getType());

		Conjunction conjunction = Restrictions.conjunction();
		Disjunction disjunction = Restrictions.disjunction();
		
		if(!StringUtils.isBlank(filterDto.getUserName())){
			criteria.createAlias("user", "u");
			conjunction.add(Restrictions.ilike("u.name", filterDto.getUserName(), MatchMode.ANYWHERE));
		}
		if(!StringUtils.isBlank(filterDto.getRating())){
			conjunction.add(Restrictions.eq("rating", filterDto.getRating()));
		}
		
		if(filterDto.getBorrowable() != null && filterDto.getBorrowable()){
			conjunction.add(Restrictions.eq("borrowable", filterDto.getBorrowable()));
		}

		getProperFilterForInputName(filterDto, criteria, disjunction);
		
		criteria.add(disjunction);
		criteria.add(conjunction);
		return criteria;
	}

	private void getProperFilterForInputName(FilterDto filterDto, Criteria criteria, Disjunction disjunction) {
		
		switch(filterDto.getType()){
			
			case "books" :  
				if(!StringUtils.isBlank(filterDto.getTitle())){
					criteria.createAlias("authors", "author");
					getBookFilter(filterDto, criteria, disjunction);
				}
				break;
			case "movies" :
				if(!StringUtils.isBlank(filterDto.getTitle())){
					criteria.createAlias("actors", "actor");
					criteria.createAlias("director", "dir");
					getMovieFilter(filterDto, criteria, disjunction);
				}
				break;
			default : 
				if(!StringUtils.isBlank(filterDto.getTitle())){
					getProductFilter(filterDto, criteria, disjunction);
				}
		}
	}

	private void getMovieFilter(FilterDto filterDto, Criteria criteria, Disjunction disjunction) {
		disjunction.add(Restrictions.ilike("title", filterDto.getTitle(), MatchMode.ANYWHERE));
		disjunction.add(Restrictions.ilike("actor.fullName", filterDto.getTitle(), MatchMode.ANYWHERE));
		disjunction.add(Restrictions.ilike("dir.fullName", filterDto.getTitle(), MatchMode.ANYWHERE));
	}

	private void getBookFilter(FilterDto filterDto, Criteria criteria, Disjunction disjunction) {
		disjunction.add(Restrictions.ilike("title", filterDto.getTitle(), MatchMode.ANYWHERE));
		disjunction.add(Restrictions.ilike("author.fullName", filterDto.getTitle(), MatchMode.ANYWHERE));
	}
	
	private void getProductFilter(FilterDto filterDto, Criteria criteria, Disjunction disjunction) {
		disjunction.add(Restrictions.ilike("title", filterDto.getTitle(), MatchMode.ANYWHERE));
	}

	private Criteria getCriteriaForType(String type) {
		Criteria criteria = null;
		switch(type){
			case "books" : 
				criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);break;
			case "movies" :
				criteria = sessionFactory.getCurrentSession().createCriteria(Movie.class); break;
			default :
				criteria = sessionFactory.getCurrentSession().createCriteria(Product.class); break;
		}
		return criteria;
	}
	
	
	
}
