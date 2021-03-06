/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011 SonarSource and Eriks Nukis
 * sonarqube@googlegroups.com
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
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.javascript.model.internal.expression;

import com.google.common.collect.Iterators;
import com.sonar.sslr.api.AstNode;
import org.sonar.plugins.javascript.api.symbols.TypeSet;
import org.sonar.javascript.model.internal.JavaScriptTree;
import org.sonar.javascript.model.internal.lexical.InternalSyntaxToken;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.declaration.BindingElementTree;
import org.sonar.plugins.javascript.api.tree.expression.ExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.RestElementTree;
import org.sonar.plugins.javascript.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.javascript.api.visitors.TreeVisitor;

import java.util.Iterator;

public class RestElementTreeImpl extends JavaScriptTree implements RestElementTree, BindingElementTree {

  private final SyntaxToken ellispis;
  private final ExpressionTree element;

  public RestElementTreeImpl(InternalSyntaxToken ellispis, ExpressionTree element) {
    super(Kind.REST_ELEMENT);
    this.ellispis = ellispis;
    this.element = element;

    addChildren(ellispis, (AstNode) element);
  }

  @Override
  public SyntaxToken ellipsis() {
    return ellispis;
  }

  @Override
  public ExpressionTree element() {
    return element;
  }

  @Override
  public Kind getKind() {
    return Kind.REST_ELEMENT;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.<Tree>singletonIterator(element);
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitRestElement(this);
  }

  @Override
  public TypeSet types() {
    return TypeSet.emptyTypeSet();
  }
}
