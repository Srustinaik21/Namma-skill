package com.nammaskill.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaskill.domain.model.Course
import com.nammaskill.domain.model.UserRole
import com.nammaskill.ui.auth.LoginViewModel
import com.nammaskill.util.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: LoginViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Namma Skill", fontWeight = FontWeight.Black) },
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            item {
                WelcomeBanner(userProfile?.name ?: "Guest", userProfile?.role ?: UserRole.TRAINEE)
            }
            
            item {
                CategorySection()
            }
            
            item {
                SectionHeader("Recommended for You", Icons.Default.Star)
            }
            
            items(MockData.courses) { course ->
                CourseCard(course)
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
                RoleSpecificSection(userProfile?.role ?: UserRole.TRAINEE)
            }
        }
    }
}

@Composable
fun WelcomeBanner(name: String, role: UserRole) {
    val roleLabel = role.name.replace("_", " ").lowercase()
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = "Namaskara, $name!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Surface(
            color = MaterialTheme.colorScheme.tertiaryContainer,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Account Type: ${roleLabel.uppercase()}",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CategorySection() {
    val categories = listOf(
        "Coding" to Icons.Default.Build,
        "Sewing" to Icons.Default.Edit,
        "Agriculture" to Icons.Default.LocationOn,
        "Electrician" to Icons.Default.Settings,
        "Plumbing" to Icons.Default.Face
    )
    
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        SectionHeader("Explore Trades", Icons.Default.List)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { (label, icon) ->
                CategoryChip(label, icon)
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        Text(text = label, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(text = course.name.take(1), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(text = course.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(text = course.centerName, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            
            Button(
                onClick = {}, 
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
            ) {
                Text("Apply", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun RoleSpecificSection(role: UserRole) {
    val message = when(role) {
        UserRole.TRAINEE -> "Found 12 free courses in your area."
        UserRole.TRAINER -> "You have 3 training requests pending."
        UserRole.JOB_SEEKER -> "New Job Openings: 5 in Mandya District."
        UserRole.JOB_PROVIDER -> "Post a new vacancy to reach 500+ skilled youth."
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = message, fontWeight = FontWeight.SemiBold)
        }
    }
}
