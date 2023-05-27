// This is a generated file. Not intended for manual editing.
package nl.hannahsten.texifyidea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static nl.hannahsten.texifyidea.psi.BibtexTypes.*;
import nl.hannahsten.texifyidea.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import nl.hannahsten.texifyidea.index.stub.BibtexEntryStub;

public class BibtexEntryImpl extends BibtexEntryImplMixin implements BibtexEntry {

  public BibtexEntryImpl(ASTNode node) {
    super(node);
  }

  public BibtexEntryImpl(BibtexEntryStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull BibtexVisitor visitor) {
    visitor.visitEntry(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BibtexVisitor) accept((BibtexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<BibtexComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BibtexComment.class);
  }

  @Override
  @NotNull
  public BibtexEndtry getEndtry() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BibtexEndtry.class));
  }

  @Override
  @Nullable
  public BibtexEntryContent getEntryContent() {
    return PsiTreeUtil.getChildOfType(this, BibtexEntryContent.class);
  }

  @Override
  @Nullable
  public BibtexId getId() {
    return PsiTreeUtil.getChildOfType(this, BibtexId.class);
  }

  @Override
  @Nullable
  public BibtexPreamble getPreamble() {
    return PsiTreeUtil.getChildOfType(this, BibtexPreamble.class);
  }

  @Override
  @NotNull
  public BibtexType getType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BibtexType.class));
  }

}
