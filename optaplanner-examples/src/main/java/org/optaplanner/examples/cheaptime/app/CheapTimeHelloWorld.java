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

package org.optaplanner.examples.cheaptime.app;

import java.io.File;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.cheaptime.domain.CheapTimeSolution;
import org.optaplanner.examples.cheaptime.persistence.CheapTimeDao;

public class CheapTimeHelloWorld {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/cheaptime/solver/cheapTimeSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        CheapTimeSolution solution = (CheapTimeSolution)new CheapTimeDao().readSolution(new File("data/cheaptime/unsolved/verysmall01.xml"));

        // Solve the problem
        solver.solve(solution);
        CheapTimeSolution bestSolution = (CheapTimeSolution) solver.getBestSolution();

        // Display the result
        System.out.println("\nSolved CheapTime:\n"
                + bestSolution);
    }
}
