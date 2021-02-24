package com.pruvn.rms.dao.abstracts;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

public interface GenericDAO {
	void setAllowCreateNew( boolean allowCreateNew );
	boolean isAllowCreateNew();
	Serializable save( final Object modelObject );
	void update( final Object modelObject );
	void merge( final Object modelObject );
	void saveOrUpdate( final Object modelObject );
	Object load( final Class clazz, final Long id );
	Object get( final Class clazz, final Long id );
	void delete( final Object modelObject );
	void flush();
	void refresh( Object entity );
	void clear();
}
