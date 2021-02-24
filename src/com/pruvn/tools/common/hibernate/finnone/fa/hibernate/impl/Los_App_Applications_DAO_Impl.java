package com.pruvn.tools.common.hibernate.finnone.fa.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.fa.LOS_APP_APPLICATIONS_FA;
import com.pruvn.tools.common.hibernate.finnone.fa.hibernate.Los_App_Applications_DAO;
import com.pruvn.tools.common.util.GlobalConstant;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Los_App_Applications_DAO_Impl extends AbstractHibernateDAO<LOS_APP_APPLICATIONS_FA, Integer> implements Los_App_Applications_DAO{

	public LOS_APP_APPLICATIONS_FA getApplicationByID(String app_id_c) {
		Criteria criteria = getSession().createCriteria(LOS_APP_APPLICATIONS_FA.class);
		return (LOS_APP_APPLICATIONS_FA)criteria.add(Restrictions.eq("app_id_c", app_id_c)).uniqueResult();	
	}

	public Object queryUnique(String[] columns, Object[] values,
			String[] operators, Class classObject) {
		Criteria criteria = getSession().createCriteria(classObject);
		ProjectionList projList = Projections.projectionList();
		boolean hasProjection = false;
		for (int i = 0; i < columns.length; i++) {
			if (GlobalConstant.HIBERNATE_OPERATOR_EQUAL.equals(operators[i])) {
				criteria.add(Restrictions.eq(columns[i], values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_IN_LIST.equals(operators[i])) {
				criteria.add(Restrictions.in(columns[i], (List<Object>)values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_NOT_EQUAL.equals(operators[i])) {
				criteria.add(Restrictions.ne(columns[i], values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_GREATER_EQUAL.equals(operators[i])) {
				criteria.add(Restrictions.ge(columns[i], values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_GREATER_THAN.equals(operators[i])) {
				criteria.add(Restrictions.gt(columns[i], values[i]));				
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_LESSER_EQUAL.equals(operators[i])) {
				criteria.add(Restrictions.le(columns[i], values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_LESS_THAN.equals(operators[i])) {
				criteria.add(Restrictions.lt(columns[i], values[i]));
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_LIKE.equals(operators[i])) {
				criteria.add(Restrictions.like(columns[i], "%" + values[i].toString() + "%"));
			}
			else if (GlobalConstant.HIBERNATE_PROJECTION_MIN.equals(operators[i])) {
				projList.add(Projections.min(columns[i]));			
				hasProjection = true;
			}
			else if (GlobalConstant.HIBERNATE_PROJECTION_MAX.equals(operators[i])) {				
				projList.add(Projections.max(columns[i]));
				hasProjection = true;
			}
			else if (GlobalConstant.HIBERNATE_PROJECTION_SUM.equals(operators[i])) {				
				projList.add(Projections.sum(columns[i]));
				hasProjection = true;
			}
			else if (GlobalConstant.HIBERNATE_PROJECTION_LIST.equals(operators[i])) {				
				projList.add(Projections.property(columns[i]));
				hasProjection = true;
			}
			else if (GlobalConstant.HIBERNATE_OPERATOR_IS_NOT_NULL.equals(operators[i])) {
				criteria.add(Restrictions.isNotNull(columns[i]));
			}
			if (hasProjection) {
				criteria.setProjection(projList);
			}
		}
		return criteria.uniqueResult();
	}

	

}
