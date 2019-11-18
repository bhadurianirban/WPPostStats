/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.wppoststats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dgrfiv
 */
public class ConvertIntoCSV {

    void createCSV(List<PostContentDTO> contentDTOs) {
        try {
            String fileName = "PuranStats";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date currDate = new Date();
            String fileExt = sdf.format(currDate);
            String filePathName = System.getProperty("user.dir")+File.separator+"Content"+fileExt+".csv";
            System.out.println(filePathName);
            FileWriter writer = new FileWriter(filePathName);
            String heading = "Word Count,Title,Post Id,Date,Compiled By\n";
            writer.write(heading);
            for (PostContentDTO pcdto : contentDTOs) {
                String collect = String.valueOf(pcdto.getWordCount())+","+pcdto.getTitle()+","+String.valueOf(pcdto.getPostId())+","+pcdto.getPostDate()+","+pcdto.getCompiledBy()+"\n";
                writer.write(collect);
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ConvertIntoCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
