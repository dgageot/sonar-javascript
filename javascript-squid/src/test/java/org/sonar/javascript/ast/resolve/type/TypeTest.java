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
package org.sonar.javascript.ast.resolve.type;

import com.sonar.sslr.api.AstNode;
import org.sonar.plugins.javascript.api.symbols.Symbol;
import org.sonar.javascript.ast.resolve.SymbolModelImpl;
import org.sonar.javascript.model.JavaScriptTreeModelTest;
import org.sonar.plugins.javascript.api.tree.ScriptTree;

import java.io.File;


public abstract class TypeTest extends JavaScriptTreeModelTest {
  protected AstNode ROOT_NODE;
  protected SymbolModelImpl SYMBOL_MODEL;

  protected Symbol getSymbol(String name) {
    return SYMBOL_MODEL.getSymbols(name).iterator().next();
  }

  protected void setUp(String filename) throws Exception {
    ROOT_NODE = p.parse(new File("src/test/resources/ast/resolve/type/", filename));
    SYMBOL_MODEL = SymbolModelImpl.create((ScriptTree) ROOT_NODE, null, null, null);
  }
}
