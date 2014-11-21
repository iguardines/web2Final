package ar.edu.uces.progweb2.booksmov.model;

public enum LoanStateEnum {
	
	PENDING{

		@Override
		public boolean canRequestProductLoan() {
			return false;
		}
		
	},
	
	ACCEPTED{

		@Override
		public boolean canRequestProductLoan() {
			return true;
		}
		
	},
	
	REJECTED{

		@Override
		public boolean canRequestProductLoan() {
			return false;
		}
		
	},
	
	DELIVERED{

		@Override
		public boolean canRequestProductLoan() {
			return true;
		}
		
	};
	
	public abstract boolean canRequestProductLoan();
}
