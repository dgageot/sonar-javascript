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
package org.sonar.javascript.model.internal.statement;

import com.google.common.collect.Iterators;
import com.sonar.sslr.api.AstNode;
import org.sonar.plugins.javascript.api.visitors.TreeVisitor;
import org.sonar.javascript.model.internal.JavaScriptTree;
import org.sonar.javascript.model.internal.lexical.InternalSyntaxToken;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.expression.IdentifierTree;
import org.sonar.plugins.javascript.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.javascript.api.tree.statement.LabelledStatementTree;
import org.sonar.plugins.javascript.api.tree.statement.StatementTree;

import java.util.Iterator;

public class LabelledStatementTreeImpl extends JavaScriptTree implements LabelledStatementTree {

  private final IdentifierTree label;
  private final SyntaxToken colon;
  private final StatementTree statement;

  public LabelledStatementTreeImpl(IdentifierTree label, InternalSyntaxToken colon, StatementTree statement) {
    super(Kind.LABELLED_STATEMENT);
    this.label = label;
    this.colon = colon;
    this.statement = statement;

    addChild((AstNode) label);
    addChild(colon);
    addChild((AstNode) statement);
  }

  @Override
  public Kind getKind() {
    return Kind.LABELLED_STATEMENT;
  }

  @Override
  public IdentifierTree label() {
    return label;
  }

  @Override
  public SyntaxToken colon() {
    return colon;
  }

  @Override
  public StatementTree statement() {
    return statement;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.<Tree>forArray(label, statement);
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitLabelledStatement(this);
  }
}
