package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TweerterEntity;

/**
 * Session Bean implementation class tweeterEJB
 */
@Stateless
@LocalBean
public class tweeterEJB {

	@PersistenceContext
	private EntityManager em;
	
    public tweeterEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void addNewTweeter(TweerterEntity tweeterEntity) {
    	
    	System.out.println("===================== adding tweeter to database===================");
    	em.persist(tweeterEntity);
    	
    }
    public List<TweerterEntity> getTweets() {
    	List<TweerterEntity> tweet = em.createQuery("Select e from tweeter_tbl e"
    			,TweerterEntity.class).getResultList();
    	return tweet;
    }

}
