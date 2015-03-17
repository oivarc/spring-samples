package eu.acengineering.sample.querybyspec.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification implements Specification<Person> {
	
	private Person filter;
	
	public PersonSpecification(Person filter) {
		super();
		this.filter = filter;
	}

	public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		Predicate p = cb.disjunction();

		if (filter.getName() != null) {
			p.getExpressions().add(cb.equal(root.get("name"), filter.getName()));
		}
		
		if (filter.getSurname() != null && filter.getAge() != null) {
			p.getExpressions().add(cb.and(
						cb.equal(root.get("surname"), filter.getSurname()),
						cb.equal(root.get("age"), filter.getAge())
					));
		}
		
		
		return p;
		
	}

	
}
