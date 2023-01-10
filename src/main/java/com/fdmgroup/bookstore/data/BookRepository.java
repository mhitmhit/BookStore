package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.Book;

public interface BookRepository<T> extends Persistable<T>, Removable<T>, Searchable<T> {

}
