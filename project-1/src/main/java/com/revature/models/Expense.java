package com.revature.models;

public class Expense {

	private int id;
	private int userId;
	private double expenseAmmount;
	private String expenseDescription;
	private String expenseStatus;
	private String reviewedBy;

	public Expense() {

	}

	public Expense(int id, int userId, double expenseAmmount, String expenseDescription, String expenseStatus, String reviewed) {
		super();
		this.id = id;
		this.userId = userId;
		this.expenseAmmount = expenseAmmount;
		this.expenseDescription = expenseDescription;
		this.expenseStatus = expenseStatus;
		this.reviewedBy = reviewed;
	}
	
	public Expense(int userId, double expenseAmmount, String expenseDescription, String expenseStatus, String reviewed) {
		super();
		this.userId = userId;
		this.expenseAmmount = expenseAmmount;
		this.expenseDescription = expenseDescription;
		this.expenseStatus = expenseStatus;
		this.reviewedBy = reviewed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getExpenseAmmount() {
		return expenseAmmount;
	}

	public void setExpenseAmmount(double expenseAmmount) {
		this.expenseAmmount = expenseAmmount;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public String getExpenseStatus() {
		return expenseStatus;
	}

	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(expenseAmmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((expenseDescription == null) ? 0 : expenseDescription.hashCode());
		result = prime * result + ((expenseStatus == null) ? 0 : expenseStatus.hashCode());
		result = prime * result + id;
		result = prime * result + ((reviewedBy == null) ? 0 : reviewedBy.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (Double.doubleToLongBits(expenseAmmount) != Double.doubleToLongBits(other.expenseAmmount))
			return false;
		if (expenseDescription == null) {
			if (other.expenseDescription != null)
				return false;
		} else if (!expenseDescription.equals(other.expenseDescription))
			return false;
		if (expenseStatus == null) {
			if (other.expenseStatus != null)
				return false;
		} else if (!expenseStatus.equals(other.expenseStatus))
			return false;
		if (id != other.id)
			return false;
		if (reviewedBy == null) {
			if (other.reviewedBy != null)
				return false;
		} else if (!reviewedBy.equals(other.reviewedBy))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", userId=" + userId + ", expenseAmmount=" + expenseAmmount
				+ ", expenseDescription=" + expenseDescription + ", expenseStatus=" + expenseStatus + ", reviewedBy="
				+ reviewedBy + "]";
	}


	
	
}