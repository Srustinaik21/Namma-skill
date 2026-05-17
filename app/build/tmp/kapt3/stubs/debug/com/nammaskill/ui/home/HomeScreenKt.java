package com.nammaskill.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\b\u0010\u0006\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00a8\u0006\u0014"}, d2 = {"CategoryChip", "", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CategorySection", "CourseCard", "course", "Lcom/nammaskill/domain/model/Course;", "HomeScreen", "viewModel", "Lcom/nammaskill/ui/auth/LoginViewModel;", "RoleSpecificSection", "role", "Lcom/nammaskill/domain/model/UserRole;", "SectionHeader", "title", "WelcomeBanner", "name", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull
    com.nammaskill.ui.auth.LoginViewModel viewModel) {
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