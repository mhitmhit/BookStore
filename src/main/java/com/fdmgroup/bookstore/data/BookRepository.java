package com.fdmgroup.bookstore.data;

public interface BookRepository<T> extends Persistable<T>, Removable<T>, Searchable<T> {

}
