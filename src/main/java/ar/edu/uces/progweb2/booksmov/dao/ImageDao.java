package ar.edu.uces.progweb2.booksmov.dao;

public interface ImageDao {
	
	byte[] getBookImage(Long id);
	
	byte[] getMovieImage(Long id);

}
