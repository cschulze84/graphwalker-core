package org.graphwalker.core.model;

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

import org.graphwalker.core.model.tree.Classification;

import static org.graphwalker.core.model.tree.Classification.ClassificationBuilder;

/**
 * @author Nils Olsson
 */
public final class ClassificationTree implements Model {

    private final Classification classification;

    private ClassificationTree(ClassificationTreeBuilder builder) {
        this.classification = builder.getClassification().build();
    }

    public Classification getClassification() {
        return classification;
    }

    public static class ClassificationTreeBuilder implements Builder<ClassificationTree> {

        private Builder<Classification> classification = ClassificationBuilder.EMPTY;

        public ClassificationTreeBuilder setClassification(ClassificationBuilder classification) {
            this.classification = classification;
            return this;
        }

        public Builder<Classification> getClassification() {
            return classification;
        }

        @Override
        public ClassificationTree build() {
            return new ClassificationTree(this);
        }
    }
}