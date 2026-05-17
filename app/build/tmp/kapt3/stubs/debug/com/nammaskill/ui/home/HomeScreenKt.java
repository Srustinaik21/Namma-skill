package com.nammaskill.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\b\u0010\u0006\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u00a8\u0006\u0016"}, d2 = {"CategoryChip", "", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CategorySection", "CourseCard", "course", "Lcom/nammaskill/domain/model/Course;", "HomeScreen", "viewModel", "Lcom/nammaskill/ui/auth/LoginViewModel;", "onLogout", "Lkotlin/Function0;", "RoleSpecificSection", "role", "Lcom/nammaskill/domain/model/UserRole;", "SectionHeader", "title", "WelcomeBanner", "name", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull
    com.nammaskill.ui.auth.LoginViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void WelcomeBanner(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CategorySection() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CategoryChip(@org.jetbrains.annotations.NotNull
    java.lang.String label, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector icon) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector icon) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CourseCard(@org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.Course course) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void RoleSpecificSection(@org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role) {
    }
}