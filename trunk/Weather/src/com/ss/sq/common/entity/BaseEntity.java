package com.ss.sq.common.entity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable, Comparable<Object> {

	@Override
	public int compareTo(Object obj) throws ClassCastException, IllegalArgumentException {
		int result = 0;
		this.getClass().cast(obj);
		Method[] thisClassMethods = this.getClass().getMethods();
		Method[] objMethods = obj.getClass().getMethods();
		if (!ArrayUtils.isEmpty(thisClassMethods) && !ArrayUtils.isEmpty(objMethods)) {
			Method thisMethod = null;
			CompareToBuilder compareToBuilder = new CompareToBuilder();
			Map<Integer, Integer> sortMap = new HashMap<Integer, Integer>();

			for (int i = 0; i < thisClassMethods.length; i++) {
				thisMethod = thisClassMethods[i];
				if (thisMethod.isAnnotationPresent(ObjectComparable.class)) {
					sortMap.put(thisMethod.getAnnotation(ObjectComparable.class).order(), i);
				}
			}

			SortedSet<Integer> sortedset = new TreeSet<Integer>(sortMap.keySet());

			Iterator<Integer> it = sortedset.iterator();
			Method objMethod = null;
			while (it.hasNext()) {
				thisMethod = thisClassMethods[sortMap.get(it.next())];
				objMethod = objMethods[ArrayUtils.indexOf(objMethods, thisMethod)];
				try {
					compareToBuilder.append(thisMethod.invoke(this), objMethod.invoke(obj));
				} catch (Exception e) {
					throw new RuntimeException("Error while invoking method", e);
				}
			}
			result = compareToBuilder.toComparison();
		}

		return result;
	}

	@Override
	public boolean equals(Object obj) throws ClassCastException, IllegalArgumentException {
		boolean result = false;
		this.getClass().cast(obj);

		Method[] thisClassMethods = this.getClass().getMethods();
		Method[] objMethods = obj.getClass().getMethods();

		if (!ArrayUtils.isEmpty(thisClassMethods) && !ArrayUtils.isEmpty(objMethods)) {
			Method thisMethod = null;
			EqualsBuilder equalBuilder = new EqualsBuilder();
			for (int i = 0; i < thisClassMethods.length; i++) {
				thisMethod = thisClassMethods[i];
				Method objMethod = null;
				if (thisMethod.isAnnotationPresent(ObjectEqual.class)) {
					objMethod = objMethods[ArrayUtils.indexOf(objMethods, thisMethod)];
					try {
						equalBuilder.append(thisMethod.invoke(this), objMethod.invoke(obj));
					} catch (Exception e) {
						throw new RuntimeException("Error while invoking method", e);
					}
				}
			}

			result = equalBuilder.isEquals();
		}
		return result;
	}

	@Override
	public int hashCode() {
		int result = 0;
		Method[] thisClassMethods = this.getClass().getMethods();
		if (!ArrayUtils.isEmpty(thisClassMethods)) {
			HashCodeBuilder hashBuilder = new HashCodeBuilder();
			Method thisMethod = null;
			for (int i = 0; i < thisClassMethods.length; i++) {
				thisMethod = thisClassMethods[i];
				if (thisMethod.isAnnotationPresent(ObjectHash.class)) {
					try {
						hashBuilder.append(thisMethod.invoke(this));
					} catch (Exception e) {
						throw new RuntimeException("Error while invoking method", e);
					}
				}
			}
			result = hashBuilder.toHashCode();
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
