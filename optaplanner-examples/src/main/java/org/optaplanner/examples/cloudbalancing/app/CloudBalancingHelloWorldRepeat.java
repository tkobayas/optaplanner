/*
 * Copyright 2012 JBoss Inc
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

package org.optaplanner.examples.cloudbalancing.app;

import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;
import org.optaplanner.examples.cloudbalancing.domain.CloudComputer;
import org.optaplanner.examples.cloudbalancing.domain.CloudProcess;
import org.optaplanner.examples.cloudbalancing.persistence.CloudBalancingGenerator;

public class CloudBalancingHelloWorldRepeat {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/cloudbalancing/solver/cloudBalancingSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        CloudBalance unsolvedCloudBalance = new CloudBalancingGenerator().createCloudBalance(200, 600);

        // 1st round
        SolverThread solverThread = new SolverThread(solver, unsolvedCloudBalance);
        solverThread.start();
        
        try {
            Thread.sleep(5000);
            solver.terminateEarly();
            solverThread.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException("SolverThread did not die.", e);
        }
        
        CloudBalance solvedCloudBalance1 = (CloudBalance) solver.getBestSolution();

        System.out.println("\n 1st solution:\n" + toDisplayString(solvedCloudBalance1));
        
        // One computer is broken!
        System.out.println("------------------------");
        List<CloudComputer> computerList = solvedCloudBalance1.getComputerList();
        CloudComputer brokenComputer = computerList.remove(0);
        System.out.println("brokenComputer = " + brokenComputer);

        List<CloudProcess> processList = solvedCloudBalance1.getProcessList();
        for (CloudProcess cloudProcess : processList) {
            if (cloudProcess.getComputer().equals(brokenComputer)) {
                System.out.println(" affected cloudProcess = " + cloudProcess);
                cloudProcess.setComputer(null);
            }
        }
        System.out.println("------------------------");
        
        // 2nd round
        SolverThread solverThread2 = new SolverThread(solver, solvedCloudBalance1);
        solverThread2.start();
        
        try {
            Thread.sleep(5000);
            solver.terminateEarly();
            solverThread2.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException("SolverThread did not die.", e);
        }
        
        CloudBalance solvedCloudBalance2 = (CloudBalance) solver.getBestSolution();

        System.out.println("\n 2nd solution:\n" + toDisplayString(solvedCloudBalance2));
    }

    public static String toDisplayString(CloudBalance cloudBalance) {
        StringBuilder displayString = new StringBuilder();
        for (CloudProcess process : cloudBalance.getProcessList()) {
            CloudComputer computer = process.getComputer();
            displayString.append("  ").append(process.getLabel()).append(" -> ")
                    .append(computer == null ? null : computer.getLabel()).append("\n");
        }
        return displayString.toString();
    }

    private static class SolverThread extends Thread implements SolverEventListener<CloudBalance> {

        private final Solver solver;
        private final CloudBalance cloudBalance;

        private SolverThread(Solver solver, CloudBalance cloudBalance) {
            this.solver = solver;
            this.cloudBalance = cloudBalance;
        }

        @Override
        public void run() { // In solver thread
            solver.addEventListener(this);
            solver.solve(cloudBalance);
        }

        @Override
        public void bestSolutionChanged(BestSolutionChangedEvent<CloudBalance> event) { // In solver thread
            //System.out.println("bestSolutionChanged : " + event);
        }

    }
}
