/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalmanFilter;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.InternationalFormatter;

/**
 *
 * @author alepapadop
 */
public class View {
    

    private JFrame              _frame;
    private JFrame              _kalman_statistics_frame;
    
    private JFormattedTextField _system_noise_sdv_text_field; 
    private JFormattedTextField _measurement_sdv_text_field;
    private JFormattedTextField _f_parameter_text_field;
    
    private JButton             _ok_close_button;
    private JButton             _ok_filter_button;
    private JButton             _close_button;
    private JButton             _file_button;
    private JButton             _list_close_button;
    
    private JList               _list_p;
    private JList               _list_k;
    
    private Boolean             _file_input_flag = Boolean.FALSE;
    
    final private int           _x_axis_scale = 5;
    final private int           _y_axis_scale = 3;
    final private int           _axis_margin = 10;
    final private int           _line_stroke = 2;
    final private int           _dot_stroke = 4;
    
    private void SetListP(JList list)
    {
        _list_p = list;
    }
    
    public JList GetListP()
    {
        return _list_p;
    }
    
    private void SetListK(JList k)
    {
        _list_k = k;
    }
    
    public JList GetListK()
    {
        return _list_k;
    }
    
    private void SetKalmanStatisticsFrame(JFrame frame)
    {
        _kalman_statistics_frame = frame;
    }
    
    public JFrame GetKalmanStatisticsFrame()
    {
        return _kalman_statistics_frame;
    }
    
    private void SetListCloseButton(JButton but)
    {
        _list_close_button = but;
    }
    
    public JButton GetListCloseButton()
    {
        return _list_close_button;
    }
    
    public void  SetFileInputFlag(Boolean bool)
    {
        _file_input_flag = bool;
    }
    
    private Boolean GetFileInputFlag()
    {
        return _file_input_flag;
    }
    
    private void SetFileButton(JButton button)
    {
        _file_button = button;
    }
    
    private JButton GetFileButton()
    {
        return _file_button;
    }
    
    private void SetOKCloseButton(JButton button)
    {
        _ok_close_button = button;
    }
    
    private JButton GetOKCloseButton()
    {
        return _ok_close_button;
    }
    
    private void SetOKFilterButton(JButton button)
    {
        _ok_filter_button = button;
    }
    
    private JButton GetOKFilterButton()
    {
        return _ok_filter_button;
    }
    
    private void SetCloseButton(JButton button)
    {
        _close_button = button;
    }
    
    private JButton GetCloseButton()
    {
        return _close_button;
    }
    
    private void SetFrame(JFrame frame)
    {
        _frame = frame;
    }
   
    public JFrame GetFrame()
    {
        return _frame;
    }
   
    private void SetSystemNoiseSDVTextField(JFormattedTextField text_field)
    {
        _system_noise_sdv_text_field = text_field;
    }
    
    private JFormattedTextField GetSystemNoiseSDVTextField()
    {
        return _system_noise_sdv_text_field;
    }
    
    private void SetMeasurementSDVTextField(JFormattedTextField text_field)
    {
        _measurement_sdv_text_field = text_field;
    }
    
    private JFormattedTextField GetSystemMeasurementSDVTextField()
    {
        return _measurement_sdv_text_field;
    }
    
    private void SetFParameterTextField(JFormattedTextField text_field)
    {
        _f_parameter_text_field = text_field;
    }
    
    private JFormattedTextField GetFParameterTextField()
    {
        return _f_parameter_text_field;
    }
    
    private InternationalFormatter CreateFormatter()
    {
        NumberFormat format = DecimalFormat.getInstance();        
        InternationalFormatter formatter = new InternationalFormatter(format);
        
        return formatter;
    }
    
    private JPanel CreateInputPanel()
    {        
        JPanel              panel = new JPanel();
        GridLayout          layout = new GridLayout();
        JLabel              label;        
        JFormattedTextField text_field;

        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        layout.setRows(3);
        layout.setColumns(2);
        layout.setVgap(10);
        layout.setHgap(10);
        
        label = new JLabel("System Noise SDV");
        text_field = new JFormattedTextField(CreateFormatter());
        panel.add(label);        
        panel.add(text_field);
        SetSystemNoiseSDVTextField(text_field);
        
        label = new JLabel("System Measurement SDV");
        text_field = new JFormattedTextField(CreateFormatter());
        panel.add(label);        
        panel.add(text_field);
        SetMeasurementSDVTextField(text_field);
        
        label = new JLabel("F Parameter");
        text_field = new JFormattedTextField(CreateFormatter());
        panel.add(label);        
        panel.add(text_field);
        SetFParameterTextField(text_field);
        
        return panel;
    }
    
    private JPanel CreateButtonPanel()
    {
        
        JPanel     button_panel = new JPanel();
        FlowLayout button_layout = new FlowLayout();
        JButton    button;
                        
        button_panel.setLayout(button_layout);
                        
        button_panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        button = new JButton("OK & Close");
        button_panel.add(button);
        SetOKCloseButton(button);
        
        button = new JButton("OK & Filter");
        button_panel.add(button);
        SetOKFilterButton(button);
        
        button = new JButton("Close");
        button_panel.add(button);
        SetCloseButton(button);
        
        return button_panel;
        
    }
    
    private JPanel CreateFilePanel()
    {
        JPanel panel = new JPanel();
        JButton button = new JButton("Open File");
        
        panel.add(button);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        SetFileButton(button);
        
        return panel;
    }
    
    private JFrame CreateFrame()
    {
        JFrame frame = new JFrame("Kalman Filter");
        
        SetFrame(frame);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.add(CreateFilePanel(), BorderLayout.NORTH);
        frame.add(CreateInputPanel(), BorderLayout.CENTER);
        frame.add(CreateButtonPanel(), BorderLayout.SOUTH);
        frame.setResizable(false);
        
        frame.pack();
        
        SetFrame(frame);
        
        return frame;
    }
    
    public void UpdateInterface()
    {
        if (_file_input_flag) {
            _measurement_sdv_text_field.setEnabled(Boolean.TRUE);
            _system_noise_sdv_text_field.setEnabled(Boolean.TRUE);
            _f_parameter_text_field.setEnabled(Boolean.TRUE);
            //_close_button.setEnabled(Boolean.TRUE);
            _ok_close_button.setEnabled(Boolean.TRUE);
            _ok_filter_button.setEnabled(Boolean.TRUE);
        } else {            
            _measurement_sdv_text_field.setEnabled(Boolean.FALSE);
            _system_noise_sdv_text_field.setEnabled(Boolean.FALSE);
            _f_parameter_text_field.setEnabled(Boolean.FALSE);
            //_close_button.setEnabled(Boolean.FALSE);
            _ok_close_button.setEnabled(Boolean.FALSE);
            _ok_filter_button.setEnabled(Boolean.FALSE);            
        }
    }
    
    public void Gui()
    { 

        CreateFrame();
        UpdateInterface();
    }
    
    private double GetFormattedTextFiedlDoubleValue(JFormattedTextField text_field)
    {
        String str;
        double ret_val;
        
        str = text_field.getText();
        if (str.length() != 0) {
            ret_val = Double.parseDouble(str);
        } else {
            ret_val = -1;
        }
        
        return ret_val;
    }
    
    public double GetFParameterTextFieldValue()
    {
        return GetFormattedTextFiedlDoubleValue(_f_parameter_text_field);
    }
    
    public double GetSytemNoiseSDVTextFieldValue()
    {
        return GetFormattedTextFiedlDoubleValue(_system_noise_sdv_text_field);
    }
    
    public double GetMeasurementNoiseSDVTextFieldValue()
    {
        return GetFormattedTextFiedlDoubleValue(_measurement_sdv_text_field);
    }
    
    public void CreateGraph(final Model model)
    {
        JFrame frame = new JFrame("Kalman Filter Graph");
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {                                               
                Shape shape;
                
                List<Double> real_val = model.GetRealValuesList();
                List<Double> noise_val = model.GetNoiseValuesList();
                List<Double> kalman_val = model.GetKalmanList();
                
                int i;
                
                int real_size = real_val.size();
                double real_max = Collections.max(real_val);
                double real_min = Collections.min(real_val);
                
                int noise_size = noise_val.size();
                double noise_max = Collections.max(noise_val);
                double noise_min = Collections.min(noise_val);
                                
                int kalman_size = kalman_val.size();
                double kalman_max = Collections.max(kalman_val);
                double kalman_min = Collections.min(kalman_val);
                
                double x_axis_lenght = real_size;
                double real_y_axis_lenght = 2 * Math.max(Math.abs(real_max), Math.abs(real_min));
                double noise_y_axis_lenght = 2 * Math.max(Math.abs(noise_max), Math.abs(noise_min));
                double kalman_y_axis_lenght = 2 * Math.max(Math.abs(kalman_max), Math.abs(kalman_min));
                
                double y_axis_lenght = Math.max(real_y_axis_lenght, Math.max(noise_y_axis_lenght, kalman_y_axis_lenght));
                
                double x_zero = 0;
                double y_zero = y_axis_lenght / 2;               
                
                if (real_size != noise_size || real_size != kalman_size) {
                    System.out.println("problem");                    
                }
                
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);
                
                         
                for (i = 0; i < real_size; ++i) {                    
                    if (i > 0) {
                        double x1, x2, y1, y2;
                                
                        x1 = (i - 1) * _x_axis_scale;
                        y1 = Math.abs(real_val.get(i - 1) - y_zero) * _y_axis_scale;
                        x2 = i * _x_axis_scale;
                        y2 = Math.abs(real_val.get(i) - y_zero) * _y_axis_scale;
                        
                        g2d.setColor(Color.BLACK);
                        shape = new Line2D.Double(x1, y1, x2, y2);
                        g2d.setStroke(new BasicStroke(_line_stroke));
                        g2d.draw(shape);
                        
                        shape = new Line2D.Double(x1, y1, x1, y1);
                        g2d.setStroke(new BasicStroke(_dot_stroke));
                        g2d.draw(shape);
                                                
                        shape = new Line2D.Double(x2, y2, x2, y2);                        
                        g2d.draw(shape);
                        
                        g2d.setColor(Color.RED);
                        x1 = (i - 1) * _x_axis_scale;
                        y1 = Math.abs(noise_val.get(i - 1) - y_zero) * _y_axis_scale;
                        x2 = i * _x_axis_scale;
                        y2 = Math.abs(noise_val.get(i) - y_zero) * _y_axis_scale;
                        shape = new Line2D.Double(x1, y1, x2, y2);
                        g2d.setStroke(new BasicStroke(_line_stroke));
                        g2d.draw(shape);
                        
                        shape = new Line2D.Double(x1, y1, x1, y1);
                        g2d.setStroke(new BasicStroke(_dot_stroke));
                        g2d.draw(shape);
                                                
                        shape = new Line2D.Double(x2, y2, x2, y2);                        
                        g2d.draw(shape);
                        
                        g2d.setColor(Color.BLUE);
                        x1 = (i - 1) * _x_axis_scale;
                        y1 = Math.abs(kalman_val.get(i - 1) - y_zero) * _y_axis_scale;
                        x2 = i * _x_axis_scale;
                        y2 = Math.abs(kalman_val.get(i) - y_zero) * _y_axis_scale;
                        shape = new Line2D.Double(x1, y1, x2, y2);
                        g2d.setStroke(new BasicStroke(_line_stroke));
                        g2d.draw(shape);
                        
                        shape = new Line2D.Double(x1, y1, x1, y1);
                        g2d.setStroke(new BasicStroke(_dot_stroke));
                        g2d.draw(shape);
                                                
                        shape = new Line2D.Double(x2, y2, x2, y2);                        
                        g2d.draw(shape);
                    }
                    
                }                           
                
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(_line_stroke));
                
                shape = new Line2D.Double(x_zero * _x_axis_scale, 
                                          y_zero * _y_axis_scale, 
                                          x_axis_lenght * _x_axis_scale, 
                                          y_zero * _y_axis_scale);                
                g2d.draw(shape);
                
                shape = new Line2D.Double(0, 
                                          0, 
                                          0, 
                                          y_axis_lenght * _y_axis_scale);                
                g2d.draw(shape);
                
                g2d.setBackground(Color.WHITE);
                                                
            }    
        };
        
        panel.setBackground(Color.WHITE);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
    }
    
    public void CreateAvgEuclideanDistance(final Model model)
    {
        JFrame      frame = new JFrame("Calculated Deviation Values");
        JPanel      panel = new JPanel();
        GridLayout  layout = new GridLayout();
        JLabel      label;
        JTextField  text_field;
        double      real_noise_avg_euclidian_distance = model.GetRealNoiseAvgEuclideanDistance();
        double      real_kalman_avg_euclidian_distance = model.GetRealKalmanAvgEuclideanDistance();
        
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        layout.setRows(2);
        layout.setColumns(2);
        layout.setVgap(10);
        layout.setHgap(10);
        
        label = new JLabel("Deviation between noise and real measurements");
        text_field = new JTextField();
        text_field.setText(Double.toString(real_noise_avg_euclidian_distance));
        text_field.setEditable(false);
        panel.add(label);
        panel.add(text_field);
        
        label = new JLabel("Deviation between Kalman filter and measurements");
        text_field = new JTextField();
        text_field.setText(Double.toString(real_kalman_avg_euclidian_distance));
        text_field.setEditable(false);
        panel.add(label);
        panel.add(text_field);
        
        frame.add(panel);
                                
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        frame.pack();        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        
    }
    
    public void CreateDualList(final Model model)
    {
        JFrame              frame = new JFrame("Kalman Filter Statistics");
        JPanel              panel;
        GridLayout          layout;
        JList               list;
        DefaultListModel    list_model;            
        JButton             button;
        int                 size;
        JScrollPane         scroll_pane;
        
        List<Double> kalman_p_list = model.GetKalmanPList();
        List<Double> kalman_k_list = model.GetKalmanKList();
        
        panel = new JPanel();
        layout = new GridLayout();
        
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        layout.setRows(1);
        layout.setColumns(2);
        layout.setVgap(10);
        layout.setHgap(10);
        
        
        list_model = new DefaultListModel();
        size = kalman_p_list.size();
        for (int i = 0; i < size; ++i) {
            list_model.addElement(kalman_p_list.get(i));
        }
        scroll_pane = new JScrollPane();
        list = new JList(list_model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll_pane.setViewportView(list);
        panel.add(scroll_pane);
        SetListP(list);
                
        list_model = new DefaultListModel();        
        size = kalman_k_list.size();
        for (int i = 0; i < size; ++i) {
            list_model.addElement(kalman_k_list.get(i));
        }
        scroll_pane = new JScrollPane();
        list = new JList(list_model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll_pane.setViewportView(list);
        panel.add(scroll_pane);
        SetListK(list);
        
        frame.add(panel, BorderLayout.CENTER);
        
        panel = new JPanel();
        layout = new GridLayout();
        
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        layout.setRows(1);
        layout.setColumns(1);
        layout.setVgap(10);
        layout.setHgap(10);
        
        button = new JButton("Close");
        panel.add(button);
        
        SetListCloseButton(button);
        
        frame.add(panel, BorderLayout.SOUTH);
        
        SetKalmanStatisticsFrame(frame);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        frame.pack();        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
    }
    
    public void GuiVisible(boolean var)
    {
        _frame.setVisible(var);
    }
    
    public void AddFileOpenButtonActionListener(ActionListener listener) 
    {
        _file_button.addActionListener(listener);
    }

    public void AddCloseButtonActionListener(ActionListener listener) 
    {
        _close_button.addActionListener(listener);
    }

    public void AddOKFilterButtonActionListener(ActionListener listener) 
    {
        _ok_filter_button.addActionListener(listener);
    }
    
    public void AddListCloseButtonActionListener(ActionListener listener) 
    {
        _list_close_button.addActionListener(listener);
    }
    
    public void AddListSelectionListener(ListSelectionListener listener)
    {
        _list_k.addListSelectionListener(listener);
        _list_p.addListSelectionListener(listener);
    }
    
    public void CreateWarningDialog()
    {
        JFrame  frame = new JFrame();
        
        JOptionPane.showMessageDialog(frame,
            "Please set the input values to proceed.",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
    }
}
