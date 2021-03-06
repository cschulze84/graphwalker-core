package org.graphwalker.core.condition;

/*
 * #%L
 * GraphWalker Core
 * %%
 * Copyright (C) 2011 - 2014 GraphWalker
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.Context;
import org.graphwalker.core.machine.TestExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * @author Nils Olsson
 */
public class AlternativeConditionTest {

    @Test
    public void testConstructor() {
        AlternativeCondition alternativeCondition = new AlternativeCondition();
        Assert.assertNotNull(alternativeCondition);
        Assert.assertNotNull(alternativeCondition.getStopConditions());
        Assert.assertThat(alternativeCondition.getStopConditions().size(), is(0));
    }

    @Test
    public void testFulfilment() {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge().setSourceVertex(v1).setTargetVertex(v2).setName("e1");
        Model model = new Model().addEdge(e1);
        StopCondition stopCondition = new AlternativeCondition()
                .addStopCondition(new VertexCoverage(100))
                .addStopCondition(new ReachedEdge("e1"));
        Context context = new TestExecutionContext(model, new RandomPath(stopCondition));
        Assert.assertThat(stopCondition.getFulfilment(context), is(0.0));
        context.setCurrentElement(v1.build());
        context.getProfiler().start();
        context.getProfiler().stop();
        Assert.assertThat(stopCondition.getFulfilment(context), is(0.5));
        context.setCurrentElement(e1.build());
        Assert.assertThat(stopCondition.getFulfilment(context), is(1.0));
    }

    @Test
    public void testIsFulfilled() {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge().setSourceVertex(v1).setTargetVertex(v2).setName("e1");
        Model model = new Model().addEdge(e1);
        StopCondition stopCondition = new AlternativeCondition()
                .addStopCondition(new VertexCoverage(100))
                .addStopCondition(new ReachedEdge("e1"));
        Context context = new TestExecutionContext(model, new RandomPath(stopCondition));
        Assert.assertFalse(stopCondition.isFulfilled(context));
        context.setCurrentElement(v1.build());
        context.getProfiler().start();
        context.getProfiler().stop();
        Assert.assertFalse(stopCondition.isFulfilled(context));
        context.setCurrentElement(e1.build());
        Assert.assertTrue(stopCondition.isFulfilled(context));
    }
}
