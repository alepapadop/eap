/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalmanFilter;

import KalmanFilter.View.*;
import KalmanFilter.Model.*;
import KalmanFilter.Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author alepapadop
 */
public class Controller {
    
    private final View view = new View();
    
    private final Model model = new Model();
    
    private void OpenFileButtonAction()
    {
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("user.home"));
        chooser.setDialogTitle("Select Text File");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);                       

        if (chooser.showOpenDialog(view.GetFrame()) == JFileChooser.APPROVE_OPTION) { 
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

            if (model.ReadFileValues(chooser.getSelectedFile().toString()) == 1) {
                view.SetFileInputFlag(Boolean.TRUE);
                view.UpdateInterface();
            }

        } else {
            System.out.println("No Selection ");
        }           
    }
    
    private int CheckInput()
    {
        int error_flag = 0;
        
        if (view.GetFParameterTextFieldValue() == -1) {
            error_flag = 1;
        }
        
        if (view.GetMeasurementNoiseSDVTextFieldValue() == -1) {
            error_flag = 1;
        }
        
        if (view.GetSytemNoiseSDVTextFieldValue() == -1) {
            error_flag = 1;
        }
        
        return error_flag;
    }
    
    private void SetDualListListeners() {
        view.AddListCloseButtonActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evt) {                    
                view.GetKalmanStatisticsFrame().dispose();
                }
            }
        );
        
        view.AddListSelectionListener( 
            new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    boolean abjust = lse.getValueIsAdjusting();
                    if (!abjust) {
                        JList list = (JList)lse.getSource();
                        int index = list.getSelectedIndex();
                        
                        if (list == view.GetListK()) {
                            view.GetListP().setSelectedIndex(index);
                            view.GetListP().ensureIndexIsVisible(index);
                        } else if (list == view.GetListP()) {
                            view.GetListK().setSelectedIndex(index);
                            view.GetListK().ensureIndexIsVisible(index);
                        } else {
                            System.out.println("list problem");
                        }
                        
                    }
                }
            }
        );
                
    }
             
    
    private void SetListeners() 
    {    
        view.AddFileOpenButtonActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {                             
                    OpenFileButtonAction();
                }
            }
        );
        
        view.AddCloseButtonActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {                             
                    view.GetFrame().dispose();
                }
            }
        );
        
        view.AddOKFilterButtonActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if (CheckInput() == 1) {
                        
                        view.CreateWarningDialog();
                        
                    } else {                 
                        
                        model.SetFParameter(view.GetFParameterTextFieldValue());
                        model.SetSystemNoiseSDV(view.GetSytemNoiseSDVTextFieldValue());
                        model.SetMeasurementNoiseSDV(view.GetMeasurementNoiseSDVTextFieldValue());                    
                        model.CalcKalmanFilter();

                        model.CalcAverageEuclidianDistance();

                        view.CreateDualList(model);
                        view.CreateAvgEuclideanDistance(model);
                        view.CreateGraph(model);
                        SetDualListListeners();
                        
                    }            
                }
            }
        );                        
    }
    

    
    public static void main(String[] args) 
    { 
        Controller app = new Controller(); 
        app.view.Gui();
        app.SetListeners();
        app.view.GuiVisible(true);
       
                
    }
}
