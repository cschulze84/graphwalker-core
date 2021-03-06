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

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * @author Nils Olsson
 */
public class CombinedConditionTest {

    @Test
    public void testConstructor() {
        CombinedCondition condition = new CombinedCondition();
        Assert.assertNotNull(condition);
        Assert.assertNotNull(condition.getStopConditions());
        Assert.assertThat(condition.getStopConditions().size(), is(0));
    }

    @Test
    public void testFulfilment() {
        CombinedCondition condition = new CombinedCondition();
        condition.addStopCondition(new Never());
        Assert.assertThat("Should be zero", condition.getFulfilment(null), is(0.0));
    }

    @Test
    public void testIsFulfilled() {
        CombinedCondition condition = new CombinedCondition();

        condition.addStopCondition(new Never());
        Assert.assertThat("Should be false", condition.isFulfilled(null), is(false));

        condition.addStopCondition(new Never());
        Assert.assertThat("Should be false", condition.isFulfilled(null), is(false));

        condition.addStopCondition(new Never());
        Assert.assertThat("Should be false", condition.isFulfilled(null), is(false));
    }
}
