import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
// import org.hibernate.id.UUIDHexGenerator;
import java.util.List;

import com.Row;

public class App {
    // 一、插入数据（新增）
    public static void insertData(String name, int count)
    {
        Configuration config = new Configuration().configure();// 创建Configuration对象并加载hibernate.cfg.xml配置文件
        SessionFactory sessionFactory = config.buildSessionFactory();// 获取SessionFactory
        Session session = sessionFactory.openSession();// 从sessionFactory 获取 Session
        Transaction transaction = session.beginTransaction();// 事务开启
        // 对User类持久化操作
        for(int i=0;i<count;i++)
        {
            Row row = new Row(); //uuid已经自动生成，无须手动添加
            row.setName(name);
            Object res = session.save(row); // 将对象(User)保存到表中
            System.out.println("id:" + res);
        }

        transaction.commit(); // 事务提交
        session.close(); // 关闭session & sessionFactory
        sessionFactory.close();
    }

    // 二、根据条件删除数据库（删除）
    public static void deleteData(String condition)
    {
		Configuration config = new Configuration().configure();// 创建Configuration对象并加载hibernate.cfg.xml配置文件
        SessionFactory sessionFactory = config.buildSessionFactory();// 获取SessionFactory
        Session session = sessionFactory.openSession();// 从sessionFactory 获取 Session
		try
        {
			Transaction transaction = session.beginTransaction();
			String hql = "delete from Row where " + condition;
			int rowsAffected = session.createQuery(hql).executeUpdate();			
			System.out.println(rowsAffected + " rows affected.");
			transaction.commit();
		}
		catch(Exception e){
			System.out.println("出错: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if (session != null)
			    session.close(); // 关闭session & sessionFactory
            if (sessionFactory != null)
                sessionFactory.close(); 
		}
	}

    // 三、根据条件更新数据库（修改）
    public static void updateData(String condition)
    {
        Configuration config = new Configuration().configure();// 创建Configuration对象并加载hibernate.cfg.xml配置文件
        SessionFactory sessionFactory = config.buildSessionFactory();// 获取SessionFactory
        Session session = sessionFactory.openSession();// 从sessionFactory 获取 Session
		try
        {
			Transaction transaction = session.beginTransaction();
			String hql = "update Row " + condition;
			int rowsAffected = session.createQuery(hql).executeUpdate();
			System.out.print(rowsAffected + " rows affected.");
			transaction.commit();
		}
		catch(Exception e){
			System.out.println("出错: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if (session != null)
			    session.close(); // 关闭session & sessionFactory
            if (sessionFactory != null)
                sessionFactory.close(); 
		}
	}

    // 四、根据条件查询数据库（查询）
    public static void queryAllData(String condition){
		
        Configuration config = new Configuration().configure();// 创建Configuration对象并加载hibernate.cfg.xml配置文件
        SessionFactory sessionFactory = config.buildSessionFactory();// 获取SessionFactory
        Session session = sessionFactory.openSession();// 从sessionFactory 获取 Session
		try
        {
			String hql = "from Row where " + condition;
			List<Row> results = session.createQuery(hql).list();
			
			if(results.size() == 0){
				
				System.out.println("找不到记录！");
			}
			else{
				
				for(Row row: results){
					
					System.out.println("编号:" + row.getId() + ",姓名:" + row.getName() );
				}
			}
		}
		catch(Exception e){
			
			System.out.println("出错: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
            if (session != null)
			    session.close(); // 关闭session & sessionFactory
            if (sessionFactory != null)
                sessionFactory.close(); 
		}
	}

    
    public static void main(String[] args) throws Exception {
        // for(int i=0;i<20;i++)
        //     insertData("test123", 1);
        queryAllData("1=1"); // "1=1" "id = '4028896c8cdd6d55018cdd6d56420002'"
        // updateData("set name = '12233330000' WHERE 1=1");
        // deleteData("1=1"); //  name = 'jing_zhong'
    }
}