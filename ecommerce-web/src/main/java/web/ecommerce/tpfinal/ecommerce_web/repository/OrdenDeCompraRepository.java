package web.ecommerce.tpfinal.ecommerce_web.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jolbox.bonecp.BoneCPDataSource;

import web.ecommerce.tpfinal.ecommerce_web.clasesDominio.Compra;

@Repository
@Transactional(readOnly = true)
public class OrdenDeCompraRepository {
	private static final Logger LOG = LoggerFactory.getLogger(OrdenDeCompraRepository.class);
	
	/************************************************************************************************************/
	private DataSource ds;

	private OrdenDeCompraRepository(){
		setDs(setupDataSource());
	}
	private static final OrdenDeCompraRepository INSTANCE = new OrdenDeCompraRepository(); 

	/* Private constructor prevents 
	 * instantiation from other classes */

	public static OrdenDeCompraRepository getInstance() { 
		return INSTANCE; 
	}

	public DataSource setupDataSource() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		String url = "jdbc:mysql://localhost/ecommerce";
		String user = "root";
		String password = "";

		// Class.forName("org.hsqldb.jdbcDriver"); 	// load the DB driver
		BoneCPDataSource ds = new BoneCPDataSource();  // create a new datasource object
		ds.setJdbcUrl(url);		// set the JDBC url
		ds.setUsername(user);				// set the username
		ds.setPassword(password);				// set the password
		ds.setMinConnectionsPerPartition(5);

		return ds;
	}
	/*************************************************************************************************************/


	@PersistenceContext
	private EntityManager entityManager;

	//Carga los changuitos a la base de datos a la tabla compra.
	public void cargarChanguitoDB(Compra compra){
		entityManager.persist(compra);
	}

	/*************************************************************************************************************/
	//Extrae los datos de la base de datos de la tabla compra en una lista.
	public List<Compra> extraerCompras(){
		TypedQuery<Compra> q = entityManager.createQuery("select a from Compra c", Compra.class);
		List<Compra> compras = q.getResultList();

		return compras;
	}

	//Carga datos a la tabla compraVerificadas.
	public void cargarCompras(Compra compra){
		entityManager.persist(compra);
	}

	//Extrae los datos de la tabla Comprasverificadas.
	public List<Compra> extraerUsuarios(){
		TypedQuery<Compra> q = entityManager.createQuery("select a from Compra c", Compra.class);
		List<Compra> usuarios = q.getResultList();

		return usuarios;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
