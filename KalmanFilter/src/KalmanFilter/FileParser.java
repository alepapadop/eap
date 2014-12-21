/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalmanFilter;


import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alepapadop
 */
public class FileParser {
    
    private List<Double> _real_list;
    private List<Double> _noise_list;
    
    protected void SetRealList(List<Double> list)
    {
        _real_list = list;
    }
    
    protected List<Double> GetRealList()
    {
        return _real_list;
    }
        
    protected void SetNoiseList(List<Double> list)
    {
        _noise_list = list;
    }
    
    protected List<Double> GetNoiseList()
    {
        return _noise_list;
    }
    
    protected int ParseFile(String path)
    {
        List<Double> real_list = new ArrayList<>();
        List<Double> noise_list = new ArrayList<>();
        
        File file = new File(path);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                
                String[] tokens = text.split(" ");
                
                try {
                    double tmp1, tmp2;
                    tmp1 = Double.parseDouble(tokens[0]);
                    tmp2 = Double.parseDouble(tokens[1]);
                    
                    real_list.add(tmp1);
                    noise_list.add(tmp2);
                
                } catch (Exception e){
                    System.out.println("Parsing problem");
                    return 0;
                }
                
            }
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            return 0;
        } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                }
        }
        
        SetRealList(real_list);
        SetNoiseList(noise_list);
        
        return 1;
    }
    
    
    
}
