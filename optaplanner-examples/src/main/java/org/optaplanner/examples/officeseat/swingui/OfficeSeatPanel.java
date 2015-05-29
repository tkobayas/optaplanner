/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.officeseat.swingui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.examples.common.swingui.SolutionPanel;
import org.optaplanner.examples.common.swingui.TangoColorFactory;
import org.optaplanner.examples.officeseat.domain.Employee;
import org.optaplanner.examples.officeseat.domain.OfficeSeat;
import org.optaplanner.examples.officeseat.domain.Seat;
import org.optaplanner.examples.officeseat.domain.SubjectMatter;


public class OfficeSeatPanel extends SolutionPanel {

    public static final String LOGO_PATH = "/org/optaplanner/examples/officeseat/swingui/officeSeatLogo.png";

    private GridLayout gridLayout;

    public OfficeSeatPanel() {
        gridLayout = new GridLayout(0, 1);
        setLayout(gridLayout);
    }

    @Override
    public boolean isRefreshScreenDuringSolving() {
        return true;
    }

    private OfficeSeat getOfficeSeat() {
        return (OfficeSeat) solutionBusiness.getSolution();
    }

    public void resetPanel(Solution solution) {
    	System.out.println("resetPanel");
    	
        removeAll();
        OfficeSeat officeSeat = (OfficeSeat) solution;
        TangoColorFactory tangoColorFactory = new TangoColorFactory();
        gridLayout.setColumns(officeSeat.getSeatCol());
        gridLayout.setRows(officeSeat.getSeatRow());
        Map<Seat, SeatPanel> seatPanelMap = new HashMap<Seat, SeatPanel>(officeSeat.getSeatList().size());
        SeatPanel unassignedPanel = new SeatPanel(null);
        seatPanelMap.put(null, unassignedPanel);
        List<Seat> seatList = officeSeat.getSeatList();
        for (Seat seat : seatList) {
        	SeatPanel seatPanel = new SeatPanel(seat);
        	add(seatPanel);
        	seatPanelMap.put(seat, seatPanel);
		}
        
        for (Employee employee : officeSeat.getEmployeeList()) {
            SeatPanel seatPanel = seatPanelMap.get(employee.getSeat());
            seatPanel.setBackground(tangoColorFactory.pickColor(employee.getJobRole()));
            seatPanel.setEmployee(employee);
        }
    }

    private class SeatPanel extends JPanel {

        public SeatPanel(Seat seat) {
        	
        	int leftWidth = 2;
        	int rightWidth = 2;
        	
        	if (seat != null) {
        		// aisle
        		if (seat.getCol() % 2 == 0) {
        			leftWidth = 16;
        		} else {
        			rightWidth = 16;
        		}
        	}
        	
        	
            setLayout(new BorderLayout(5, 0));
            setBorder(BorderFactory.createCompoundBorder(
            		BorderFactory.createEmptyBorder(2, leftWidth, 2, rightWidth),
                    BorderFactory.createLineBorder(Color.DARK_GRAY)));
                    
            Employee dummyEmployee = new Employee();
            dummyEmployee.setSeat(seat);
            setEmployee(dummyEmployee);
        }

        public void setEmployee(Employee employee) {
            removeAll();

            if (employee.getCode() == 0) {
                add(new JLabel("Empty seat"), BorderLayout.CENTER);
                return;
            }
            Seat seat = employee.getSeat();

            JPanel jobPanel = new JPanel();
            jobPanel.setLayout(new BoxLayout(jobPanel, BoxLayout.Y_AXIS));
            jobPanel.setOpaque(false);
            jobPanel.add(new JLabel(employee.getName()));
            jobPanel.add(new JLabel(" "));
            jobPanel.setOpaque(false);
            jobPanel.add(new JLabel(employee.getJobDomain().getCode() + "_" + employee.getJobRole().getCode()));
            jobPanel.add(new JLabel(" "));
            List<SubjectMatter> subjectMatterList = employee.getSubjectMatterList();
            for (SubjectMatter subjectMatter : subjectMatterList) {
            	jobPanel.add(new JLabel(subjectMatter.getCode().substring(0, subjectMatter.getCode().length() > 10 ? 10 : subjectMatter.getCode().length())));
			}

            add(jobPanel, BorderLayout.CENTER);
        }

    }

}
