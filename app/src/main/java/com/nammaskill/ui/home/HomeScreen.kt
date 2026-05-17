package com.nammaskill.ui.home

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
fun HomeScreen(viewModel: LoginViewModel, onLogout: () -> Unit) {
    val userProfile by viewModel.userProfile.collectAsState()
    var showMenu by remember { mutableStateOf(false) }
    
    // Manage local list for dynamic adding
    val courseList = remember { mutableStateListOf<Course>().apply { addAll(MockData.courses) } }
    
    var showApplyDialog by remember { mutableStateOf<Course?>(null) }
    var showAddJobDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text("Namma Skill", fontWeight = FontWeight.Black, letterSpacing = 1.sp) 
                },
                actions = {
                    Box {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(32.dp))
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Profile") },
                                onClick = { showMenu = false },
                                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
                            )
                            Divider()
                            DropdownMenuItem(
                                text = { Text("Logout", color = MaterialTheme.colorScheme.error) },
                                onClick = { 
                                    showMenu = false
                                    viewModel.logout()
                                    onLogout()
                                },
                                leadingIcon = { Icon(Icons.Default.ExitToApp, contentDescription = null, tint = MaterialTheme.colorScheme.error) }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            if (userProfile?.role == UserRole.TRAINER || userProfile?.role == UserRole.JOB_PROVIDER) {
                ExtendedFloatingActionButton(
                    onClick = { showAddJobDialog = true },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Add Listing") },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                WelcomeBanner(userProfile?.name ?: "Guest", userProfile?.role ?: UserRole.TRAINEE)
            }
            
            item {
                CategorySection()
            }
            
            item {
                SectionHeader(
                    if (userProfile?.role == UserRole.TRAINER || userProfile?.role == UserRole.JOB_PROVIDER) 
                        "Manage Listings" else "Top Opportunities", 
                    Icons.Default.Info
                )
            }
            
            items(courseList) { course ->
                CourseCard(
                    course = course, 
                    role = userProfile?.role ?: UserRole.TRAINEE,
                    onApplyClick = { showApplyDialog = course }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
                RoleSpecificSection(userProfile?.role ?: UserRole.TRAINEE)
            }
        }

        // Dialogs
        if (showApplyDialog != null) {
            ApplyDialog(
                courseName = showApplyDialog?.name ?: "",
                onDismiss = { showApplyDialog = null },
                onConfirm = { /* Handle successful application */ }
            )
        }

        if (showAddJobDialog) {
            AddListingDialog(
                onDismiss = { showAddJobDialog = false },
                onAdd = { newCourse ->
                    courseList.add(0, newCourse)
                    showAddJobDialog = false
                }
            )
        }
    }
}

@Composable
fun WelcomeBanner(name: String, role: UserRole) {
    val roleLabel = role.name.replace("_", " ").lowercase()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Namaskara,",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
            Text(
                text = name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = roleLabel.uppercase(),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun CategorySection() {
    val categories = listOf(
        "Coding" to Icons.Default.Build,
        "Sewing" to Icons.Default.Face,
        "Agriculture" to Icons.Default.LocationOn,
        "Electric" to Icons.Default.Settings,
        "Plumbing" to Icons.Default.Build,
        "Auto" to Icons.Default.Home
    )
    
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
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
                .size(72.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon, 
                contentDescription = null, 
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = label, 
            style = MaterialTheme.typography.labelMedium, 
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun CourseCard(
    course: Course, 
    role: UserRole,
    onApplyClick: () -> Unit
) {
    var applied by remember { mutableStateOf(false) }
    val isManagement = role == UserRole.TRAINER || role == UserRole.JOB_PROVIDER
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (applied) MaterialTheme.colorScheme.secondaryContainer 
                            else MaterialTheme.colorScheme.tertiaryContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (applied) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    } else {
                        Text(
                            text = course.name.take(1), 
                            fontWeight = FontWeight.Black, 
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
                
                Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                    Text(
                        text = course.name, 
                        fontWeight = FontWeight.Bold, 
                        style = MaterialTheme.typography.titleMedium,
                        lineHeight = 20.sp
                    )
                    Text(
                        text = course.centerName, 
                        style = MaterialTheme.typography.bodySmall, 
                        color = Color.Gray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = course.duration, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                    }
                    if (course.isFree) {
                        Text(
                            text = "FREE", 
                            color = Color(0xFF4CAF50), 
                            fontWeight = FontWeight.Black, 
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                
                if (!isManagement) {
                    Button(
                        onClick = { 
                            onApplyClick()
                            applied = true 
                        }, 
                        enabled = !applied,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (applied) Color.LightGray else MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(if (applied) "Applied" else "Apply Now", fontWeight = FontWeight.Bold)
                    }
                } else {
                    OutlinedButton(
                        onClick = { /* Edit listing logic */ },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Edit Listing")
                    }
                }
            }
        }
    }
}

@Composable
fun ApplyDialog(courseName: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Apply for $courseName") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Please provide basic details to the center.")
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") })
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Current Village/Town") })
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(); onDismiss() }) {
                Text("Confirm Application")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun AddListingDialog(onDismiss: () -> Unit, onAdd: (Course) -> Unit) {
    var name by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var isFree by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Skill Listing") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Course/Job Name") })
                OutlinedTextField(value = duration, onValueChange = { duration = it }, label = { Text("Duration (e.g. 3 Months)") })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isFree, onCheckedChange = { isFree = it })
                    Text("Free Training")
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onAdd(Course(
                    id = System.currentTimeMillis().toString(),
                    name = name,
                    duration = duration,
                    isFree = isFree,
                    centerName = "My Training Center",
                    availableSeats = 20
                ))
            }) {
                Text("Create")
            }
        }
    )
}

@Composable
fun RoleSpecificSection(role: UserRole) {
    val message = when(role) {
        UserRole.TRAINEE -> "Enroll in 12+ free courses today!"
        UserRole.TRAINER -> "3 New batches assigned to you."
        UserRole.JOB_SEEKER -> "5 Jobs matching your profile found."
        UserRole.JOB_PROVIDER -> "Reach 500+ candidates instantly."
    }
    
    val bgColor = when(role) {
        UserRole.TRAINEE -> MaterialTheme.colorScheme.secondaryContainer
        UserRole.TRAINER -> MaterialTheme.colorScheme.tertiaryContainer
        UserRole.JOB_SEEKER -> Color(0xFFFFF3E0)
        UserRole.JOB_PROVIDER -> Color(0xFFF3E5F5)
    }

    val icon = when(role) {
        UserRole.TRAINEE -> Icons.Default.ThumbUp
        UserRole.TRAINER -> Icons.Default.Star
        UserRole.JOB_SEEKER -> Icons.Default.Info
        UserRole.JOB_PROVIDER -> Icons.Default.Email
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = message, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}
