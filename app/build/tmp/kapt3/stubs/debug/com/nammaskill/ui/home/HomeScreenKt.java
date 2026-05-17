package com.nammaskill.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a.\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a&\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a.\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u001e\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001af\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a&\u0010\'\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u0010\u0010(\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u001a\u0018\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0018\u0010+\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u00a8\u0006,"}, d2 = {"AddListingDialog", "", "onDismiss", "Lkotlin/Function0;", "onAdd", "Lkotlin/Function1;", "Lcom/nammaskill/domain/model/Course;", "ApplyDialog", "courseName", "", "onConfirm", "CategoryChip", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "isSelected", "", "onClick", "CategorySection", "selectedCategory", "onSelect", "CourseCard", "course", "role", "Lcom/nammaskill/domain/model/UserRole;", "isApplied", "onApplyClick", "HomeScreen", "viewModel", "Lcom/nammaskill/ui/auth/LoginViewModel;", "onLogout", "MainDashboard", "name", "onCategorySelect", "courses", "", "appliedIds", "", "onApplyRequest", "ProfileDialog", "RoleSpecificSection", "SectionHeader", "title", "WelcomeBanner", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull
    com.nammaskill.ui.auth.LoginViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void MainDashboard(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role, @org.jetbrains.annotations.Nullable
    java.lang.String selectedCategory, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCategorySelect, @org.jetbrains.annotations.NotNull
    java.util.List<com.nammaskill.domain.model.Course> courses, @org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.String> appliedIds, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.nammaskill.domain.model.Course, kotlin.Unit> onApplyRequest) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void WelcomeBanner(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CategorySection(@org.jetbrains.annotations.Nullable
    java.lang.String selectedCategory, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CategoryChip(@org.jetbrains.annotations.NotNull
    java.lang.String label, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector icon, boolean isSelected, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector icon) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CourseCard(@org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.Course course, @org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role, boolean isApplied, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onApplyClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ApplyDialog(@org.jetbrains.annotations.NotNull
    java.lang.String courseName, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AddListingDialog(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.nammaskill.domain.model.Course, kotlin.Unit> onAdd) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ProfileDialog(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void RoleSpecificSection(@org.jetbrains.annotations.NotNull
    com.nammaskill.domain.model.UserRole role) {
    }
}