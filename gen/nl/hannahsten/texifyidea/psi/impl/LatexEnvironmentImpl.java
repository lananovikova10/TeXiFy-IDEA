// This is a generated file. Not intended for manual editing.
package nl.hannahsten.texifyidea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static nl.hannahsten.texifyidea.psi.LatexTypes.*;
import nl.hannahsten.texifyidea.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import nl.hannahsten.texifyidea.index.stub.LatexEnvironmentStub;

public class LatexEnvironmentImpl extends LatexEnvironmentImplMixin implements LatexEnvironment {

  public LatexEnvironmentImpl(ASTNode node) {
    super(node);
  }

  public LatexEnvironmentImpl(LatexEnvironmentStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitEnvironment(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LatexBeginCommand getBeginCommand() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, LatexBeginCommand.class));
  }

  @Override
  @Nullable
  public LatexEndCommand getEndCommand() {
    return PsiTreeUtil.getChildOfType(this, LatexEndCommand.class);
  }

  @Override
  @Nullable
  public LatexEnvironmentContent getEnvironmentContent() {
    return PsiTreeUtil.getChildOfType(this, LatexEnvironmentContent.class);
  }

}
