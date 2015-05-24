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

package org.optaplanner.examples.officeseat.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.persistence.AbstractSolutionImporter;
import org.optaplanner.examples.common.persistence.SolutionDao;
import org.optaplanner.examples.common.swingui.SolutionPanel;
import org.optaplanner.examples.officeseat.persistence.OfficeSeatDao;
import org.optaplanner.examples.officeseat.persistence.OfficeSeatImporter;
import org.optaplanner.examples.officeseat.swingui.OfficeSeatPanel;

public class OfficeSeatApp extends CommonApp {

    public static final String SOLVER_CONFIG
            = "org/optaplanner/examples/officeseat/solver/officeSeatSolverConfig.xml";

    public static void main(String[] args) {
        prepareSwingEnvironment();
        new OfficeSeatApp().init();
    }

    public OfficeSeatApp() {
        super("Office seat arrangement",
                "Arrange office seating based on employees attributes.",
                OfficeSeatPanel.LOGO_PATH);
    }

    @Override
    protected Solver createSolver() {
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        return solverFactory.buildSolver();
    }

    @Override
    protected SolutionPanel createSolutionPanel() {
        return new OfficeSeatPanel();
    }

    @Override
    protected SolutionDao createSolutionDao() {
        return new OfficeSeatDao();
    }

    @Override
    protected AbstractSolutionImporter createSolutionImporter() {
        return new OfficeSeatImporter();
    }

}
