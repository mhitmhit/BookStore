package com.fdmgroup.bookstore.model;

public class EBook extends Book {
	
	private double sizeMegaBytes;

	public EBook(double sizeMegaBytes) {
		super();
		this.sizeMegaBytes = sizeMegaBytes;
	}

	public EBook() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(sizeMegaBytes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		EBook other = (EBook) obj;
		if (Double.doubleToLongBits(sizeMegaBytes) != Double.doubleToLongBits(other.sizeMegaBytes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EBook [sizeMegaBytes=" + sizeMegaBytes + "]";
	}
	
}
