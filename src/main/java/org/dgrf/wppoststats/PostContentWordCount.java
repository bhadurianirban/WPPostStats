/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.wppoststats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostContentWordCount {

    public static void main(String[] args) {
        try {
            getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(PostContentWordCount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getResultSet() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/purankoshdb?useSSL=false";
        String user = "bhadurianirban";
        String password = "Jhom2brishti";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            Calendar calStart = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
            calStart.add(Calendar.MONTH, -12);
            String dumpStartDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calStart.getTime());
            Calendar calEnd = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
            String dumpEndDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calEnd.getTime());
            System.out.println(dumpStartDate+" "+dumpEndDate);
            
            String startDate = "2017-05-31 11:59:59";

            String endDate = "2019-07-31 11:59:59";
            String sql = "SELECT "
                    + "post.post_content,"
                    + "post.post_title,"
                    + "post.ID,"
                    + "DATE(post.post_date),"
                    // + "Year(post.post_date),"
                    + "users.display_name "
                    + "FROM "
                    + "purankoshdb.pk_posts post INNER JOIN "
                    + "purankoshdb.pk_postmeta postmeta INNER JOIN "
                    + "purankoshdb.pk_users users "
                    + "ON post.ID = postmeta.post_id "
                    + "and post.post_status = 'publish' "
                    + "and postmeta.meta_key = 'compile_author' "
                    + "and postmeta.meta_value = users.ID "
                    + "and post.post_date BETWEEN '" + dumpStartDate + "' AND '" + dumpEndDate + "' "
                    + "order by users.display_name;";

            ResultSet rs = stmt.executeQuery(sql);
            List<PostContentDTO> contentDTOs = new ArrayList<>();
            while (rs.next()) {
                PostContentDTO postContentDTO = new PostContentDTO();
                postContentDTO.setPostContent(rs.getString(1));
                postContentDTO.setTitle(rs.getString(2));
                postContentDTO.setPostId(rs.getInt(3));
                postContentDTO.setPostDate(rs.getString(4));
                //postContentDTO.setYear(rs.getString(5));
                postContentDTO.setCompiledBy(rs.getString(5));

                String postContent = rs.getString(1);
                postContent = postContent.replaceAll("\\s*\\[[^\\]]*\\]\\s*", " ");
                StringTokenizer tokens = new StringTokenizer(postContent);
                postContentDTO.setWordCount(tokens.countTokens());

                contentDTOs.add(postContentDTO);
            }
            ConvertIntoCSV intoCSV = new ConvertIntoCSV();
            intoCSV.createCSV(contentDTOs);
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostContentWordCount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
