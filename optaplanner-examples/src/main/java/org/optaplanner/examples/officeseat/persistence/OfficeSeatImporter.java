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

package org.optaplanner.examples.officeseat.persistence;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.examples.common.persistence.AbstractTxtSolutionImporter;
import org.optaplanner.examples.officeseat.domain.Employee;
import org.optaplanner.examples.officeseat.domain.JobDomain;
import org.optaplanner.examples.officeseat.domain.JobRole;
import org.optaplanner.examples.officeseat.domain.OfficeSeat;
import org.optaplanner.examples.officeseat.domain.Seat;
import org.optaplanner.examples.officeseat.domain.SubjectMatter;

public class OfficeSeatImporter extends AbstractTxtSolutionImporter {

    public static void main(String[] args) {
        new OfficeSeatImporter().convertAll();
    }

    public OfficeSeatImporter() {
        super(new OfficeSeatDao());
    }

    public TxtInputBuilder createTxtInputBuilder() {
        return new OfficeseatInputBuilder();
    }

    public static class OfficeseatInputBuilder extends TxtInputBuilder {

        public Solution readSolution() throws IOException {
            OfficeSeat officeSeat = new OfficeSeat();
            officeSeat.setId(0L);

            readSeatList(officeSeat);
            readGuestList(officeSeat);

            BigInteger possibleSolutionSize = BigInteger.valueOf(officeSeat.getEmployeeList().size()).pow(
            		officeSeat.getSeatList().size());
            logger.info("OfficeSeat {} employees and {} seats"
                    + " with a search space of {}.",
                    getInputId(),
                    officeSeat.getEmployeeList().size(),
                    officeSeat.getSeatList().size(),
                    getFlooredPossibleSolutionSize(possibleSolutionSize));
            return officeSeat;
        }

        private void readSeatList(OfficeSeat officeSeat)
                throws IOException {
            int seatCol = readIntegerValue("SeatCol:");
            int seatRow = readIntegerValue("SeatRow:");
            
            officeSeat.setSeatCol(seatCol);
            officeSeat.setSeatRow(seatRow);

            List<Seat> seatList = new ArrayList<Seat>(seatCol * seatRow);
            for (int i = 0; i < seatRow; i++) {
                for (int j = 0; j < seatCol; j++) {
                    Seat seat = new Seat();
                    seat.setId((long) ((i * seatRow) + j));
                    seat.setCol(j);
                    seat.setRow(i);
                    seatList.add(seat);
                }
            }
            officeSeat.setSeatList(seatList);
        }

        private void readGuestList(OfficeSeat officeSeat)
                throws IOException {
            readConstantLine("Code,Name,JobDomain,JobRole,SM1,SM2,SM3,...");
            readConstantLine("\\-+");
            int guestSize = officeSeat.getSeatList().size();

            List<Employee> employeeList = new ArrayList<Employee>();
            long id = 0;
            while (bufferedReader.ready()) {
                Employee employee = new Employee();
                employee.setId((long) id);
                id++;
                String[] lineTokens = splitByCommaAndTrim(bufferedReader.readLine(), 4, 12);
                employee.setCode(Long.parseLong(lineTokens[0]));
                employee.setName(lineTokens[1]);
                employee.setJobDomain(JobDomain.valueOfCode(lineTokens[2]));
                employee.setJobRole(JobRole.valueOfCode(lineTokens[3]));

                List<SubjectMatter> subjectMatterList = new ArrayList<SubjectMatter>();
                for (int j = 4; j < lineTokens.length; j++) {
                    subjectMatterList.add(SubjectMatter.valueOfCode(lineTokens[j]));
                }
                employee.setSubjectMatterList(subjectMatterList);
                
                // don't set planning variable 'seat'
                
                employeeList.add(employee);
            }
            officeSeat.setEmployeeList(employeeList);
        }
    }

}
