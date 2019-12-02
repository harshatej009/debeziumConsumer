package harsh.rane.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import harsh.rane.model.TargetTable;

@Repository
public class TargetTableDao 
{
	private static final Logger LOGGER = LogManager.getLogger(TargetTableDao.class);
	@Autowired
	private SessionFactory sessionfactory; 
	Session session=null;
	
	public void UpdateTargetDetails(Integer id,String name)
	{
		session=sessionfactory.openSession();
		session.beginTransaction();
		TargetTable fd=new TargetTable();
		fd.setCfid(id);
		fd.setCfname(name);
		session.saveOrUpdate(fd);
		session.getTransaction().commit();
		session.close();
		LOGGER.info("Data saved successfuly in TargetTable");
		
	}
}
