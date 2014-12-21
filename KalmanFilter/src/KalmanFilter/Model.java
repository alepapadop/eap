/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalmanFilter;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author alepapadop
 */
public class Model extends FileParser {
    
    private String          _file_path;
    private List<Double>    _kalman_list;
    private List<Double>    _kalman_p_list;
    private List<Double>    _kalman_k_list;
    private double          _f_parameter;
    private double          _system_noise_sdv;
    private double          _measurement_noise_sdv;
    private double          _real_noise_avg_euclidean_distance;
    private double          _real_kalman_avg_euclidean_distance;
    
    private void SetKalmanList(List<Double> list)
    {
        _kalman_list =list;
    }
    
    public List<Double> GetKalmanList()
    {
        return _kalman_list;
    }
    
    private void SetKalmanKList(List<Double> list)
    {
        _kalman_k_list = list;
    }
    
    private void SetKalmanPList(List<Double> list)
    {
        _kalman_p_list = list;
    }
    
    public List<Double> GetKalmanKList()
    {
        return _kalman_k_list;
    }
        
    public List<Double> GetKalmanPList()
    {
        return _kalman_p_list;
    }
    
    public void CalcKalmanFilter()
    {
        //System.out.println("f = " + _f_parameter);
        //System.out.println("system = " + _system_noise_sdv);
        //System.out.println("measure = " + _measurement_noise_sdv);
        List<Double>    noise = GetNoiseValuesList();
        List<Double>    kalman = new ArrayList<>();
        List<Double>    kalman_p = new ArrayList<>();
        List<Double>    kalman_k = new ArrayList<>();
        
        int size = noise.size();
        double x_m, p_m, k;
        double x_p = noise.get(0);
        double p_p = 1;
        double f = _f_parameter;
        double q = _system_noise_sdv;
        double r = _measurement_noise_sdv;        
        
        for (int i = 0; i < size; ++i) {        
            x_m = f * x_p;            
            p_m = p_p + q*q;            
            k = p_m / (p_m + r*r);            
            x_p = x_m + k * (noise.get(i) - x_m);            
            p_p = (1 - k) * p_m;            
            kalman.add(x_p);
            kalman_k.add(k);
            kalman_p.add(p_m);
        }
        
        SetKalmanList(kalman);
        SetKalmanKList(kalman_k);
        SetKalmanPList(kalman_p);
    }

    public void SetFParameter(double f_parameter)
    {
        _f_parameter = f_parameter;
    }
    
    public void SetSystemNoiseSDV(double system_noise_sdv)
    {
        _system_noise_sdv = system_noise_sdv;
    }
    
    public void SetMeasurementNoiseSDV(double measurement_noise_sdv)
    {
        _measurement_noise_sdv = measurement_noise_sdv;
    }
    
    public void SetFilePath(String path)
    {
        _file_path = path;
    }
    
    public String GetFilePath()
    {
        return _file_path;
    }
    
    public int ReadFileValues(String path)
    {
        return ParseFile(path);
    }
    
    public List<Double> GetRealValuesList()
    {
        return GetRealList();
    }
    
    public List<Double> GetNoiseValuesList()
    {
        return GetNoiseList();
    }
    
    private double AverageEuclideanDistance(List<Double> list_a, List<Double> list_b)
    {
        double avg_euclidean_distance;
        int size_a = list_a.size();
        int size_b = list_b.size();
        double sum = 0;
        
        if (size_a != size_b) {
            System.out.println("Problem in CacAverageEuclideanDistance");
            return -1;
        }
        
        for (int i = 0; i < size_a; ++i) {
            sum = sum + Math.sqrt(Math.pow(list_a.get(i) - list_b.get(i), 2));            
        }
        
        avg_euclidean_distance = sum / size_a;
        
        return avg_euclidean_distance;
    }
    
    private double CalcRealNoiseAvgEuclideanDistance()
    {
        return AverageEuclideanDistance(GetRealList(), GetNoiseList());
    }
    
    private double CalcRealKalmanAvgEuclideanDistance()
    {
        return AverageEuclideanDistance(GetRealList(), GetKalmanList());
    }
    
    private void SetRealNoiseAvgEuclideanDistance(double val)
    {
        _real_noise_avg_euclidean_distance = val;
    }
        
    public double GetRealNoiseAvgEuclideanDistance()
    {
        return _real_noise_avg_euclidean_distance;
    }
        
    private void SetRealKalmanAvgEuclideanDistance(double val)
    {
        _real_kalman_avg_euclidean_distance = val;
    }
        
    public double GetRealKalmanAvgEuclideanDistance()
    {
        return _real_kalman_avg_euclidean_distance;
    }
    
    public void CalcAverageEuclidianDistance()
    {
        SetRealNoiseAvgEuclideanDistance(CalcRealNoiseAvgEuclideanDistance());
        SetRealKalmanAvgEuclideanDistance(CalcRealKalmanAvgEuclideanDistance());
    }
}
