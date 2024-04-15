package dbSample;

import java.awt.RadialGradientPaint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBConnectSample01 
{

    public static void main(String[] args) 
    {
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try 
      {
         //1. ドライバーのクラスをJava上で読み込む
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. DBと接続する
        con = DriverManager.getConnection
                (
                "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "DataFromScratch2318!"
                 );

        // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
        stmt = con.createStatement();

        // 4, 5. Select文の実行と結果を格納／代入
        String sql = "SELECT * FROM country LIMIT 50";
        rs = stmt.executeQuery(sql);

        // 6. 結果を表示する
        while ( rs.next())
        {
            //Name列の値を取得
            String name = rs.getString("Name");
            //Population列の値を取得
            int population = rs.getInt("Population");
            
            //取得した値を表示
            System.out.println(name);
            System.out.println(population);
        }
      }
      catch (ClassNotFoundException e) 
      {
        System.err.println("JDBCドライバーのロードに失敗しました。");
        e.printStackTrace();
      }
      catch (SQLException e) 
      {
        e.printStackTrace();
      }
            
      finally
      {
       // 7. 接続を閉じる 
          if (rs != null)
          {
              try 
              {
                  rs.close();
              }
              catch (SQLException e)
              {
                  System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                  e.printStackTrace();
              }
          }
          if (stmt != null)
          {
              try 
              {
                  stmt.close();
              }
                
              catch (Exception e) {
                // TODO: handle exception
            }
          }
          if (con != null) 
          {
              try 
              {
                  con.close();
              }
              
              catch (SQLException e) 
              {
                  System.err.println("データベース切断時いエラーが発生しました。");
                  e.printStackTrace();
              }
         }
     }
    }
}


