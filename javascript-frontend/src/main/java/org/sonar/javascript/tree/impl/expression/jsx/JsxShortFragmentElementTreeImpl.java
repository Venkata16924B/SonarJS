/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.javascript.tree.impl.expression.jsx;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.List;
import org.sonar.javascript.tree.impl.JavaScriptTree;
import org.sonar.plugins.javascript.api.symbols.TypeSet;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.expression.jsx.JsxChildTree;
import org.sonar.plugins.javascript.api.tree.expression.jsx.JsxEmptyClosingElementTree;
import org.sonar.plugins.javascript.api.tree.expression.jsx.JsxEmptyOpeningElementTree;
import org.sonar.plugins.javascript.api.tree.expression.jsx.JsxShortFragmentElementTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitor;

public class JsxShortFragmentElementTreeImpl extends JavaScriptTree implements JsxShortFragmentElementTree {

  private final JsxEmptyOpeningElementTree openingElement;
  private final List<JsxChildTree> children;
  private final JsxEmptyClosingElementTree closingElement;

  public JsxShortFragmentElementTreeImpl(JsxEmptyOpeningElementTree openingElement, List<JsxChildTree> children, JsxEmptyClosingElementTree closingElement) {
    this.openingElement = openingElement;
    this.children = children;
    this.closingElement = closingElement;
  }


  @Override
  public JsxEmptyOpeningElementTree openingElement() {
    return openingElement;
  }

  @Override
  public List<JsxChildTree> children() {
    return children;
  }

  @Override
  public JsxEmptyClosingElementTree closingElement() {
    return closingElement;
  }

  @Override
  public TypeSet types() {
    return TypeSet.emptyTypeSet();
  }

  @Override
  public Kind getKind() {
    return Kind.JSX_SHORT_FRAGMENT_ELEMENT;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.concat(
      Iterators.singletonIterator(openingElement),
      children.iterator(),
      Iterators.singletonIterator(closingElement)
    );
  }

  @Override
  public void accept(DoubleDispatchVisitor visitor) {
    visitor.visitJsxShortFragmentElement(this);
  }
}
