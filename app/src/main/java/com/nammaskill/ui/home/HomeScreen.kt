package com.nammaskill.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    var currentTab by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    // Search and Filter states for Explore
    var searchQuery by remember { mutableStateOf("") }
    var filterFreeOnly by remember { mutableStateOf(false) }
    
    // Manage local list for dynamic adding
    val fullCourseList = remember { mutableStateListOf<Course>().apply { addAll(MockData.courses) } }
    
    // Derived lists based on role and filters
    val appliedCourseIds = remember { mutableStateOf(setOf<String>()) }
    
    val exploreResults = remember(searchQuery, filterFreeOnly, fullCourseList.size) {
        fullCourseList.filter { 
            (it.name.contains(searchQuery, ignoreCase = true) || it.centerName.contains(searchQuery, ignoreCase = true)) &&
            (!filterFreeOnly || it.isFree)
        }
    }

    var showApplyDialog by remember { mutableStateOf<Course?>(null) }
    var showAddJobDialog by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }

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
                                text = { Text("View Profile") },
                                onClick = { 
                                    showMenu = false
                                    showProfileDialog = true 
                                },
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
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                val role = userProfile?.role ?: UserRole.TRAINEE
                
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                
                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
                    label = { Text("Explore") }
                )

                val actionLabel = if (role == UserRole.TRAINER || role == UserRole.JOB_PROVIDER) "Manage" else "Applied"
                val actionIcon = if (role == UserRole.TRAINER || role == UserRole.JOB_PROVIDER) Icons.Default.List else Icons.Default.CheckCircle
                
                NavigationBarItem(
                    selected = currentTab == 2,
                    onClick = { currentTab = 2 },
                    icon = { Icon(actionIcon, contentDescription = actionLabel) },
                    label = { Text(actionLabel) }
                )
            }
        },
        floatingActionButton = {
            if (userProfile?.role == UserRole.TRAINER || userProfile?.role == UserRole.JOB_PROVIDER) {
                ExtendedFloatingActionButton(
                    onClick = { showAddJobDialog = true },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Create Post") },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when(currentTab) {
                0 -> MainDashboard(
                    userProfile?.name ?: "Guest", 
                    userProfile?.role ?: UserRole.TRAINEE,
                    selectedCategory,
                    onCategorySelect = { selectedCategory = if (selectedCategory == it) null else it },
                    fullCourseList.filter { selectedCategory == null || it.category.contains(selectedCategory!!, ignoreCase = true) },
                    appliedCourseIds.value,
                    onApplyRequest = { showApplyDialog = it }
                )
                1 -> ExploreScreen(
                    searchQuery = searchQuery,
                    onSearchChange = { searchQuery = it },
                    filterFreeOnly = filterFreeOnly,
                    onFilterChange = { filterFreeOnly = it },
                    results = exploreResults,
                    appliedIds = appliedCourseIds.value,
                    role = userProfile?.role ?: UserRole.TRAINEE,
                    onApplyRequest = { showApplyDialog = it }
                )
                2 -> ManagementScreen(
                    role = userProfile?.role ?: UserRole.TRAINEE,
                    courses = fullCourseList,
                    appliedIds = appliedCourseIds.value
                )
            }
        }

        // Dialogs
        if (showApplyDialog != null) {
            ApplyDialog(
                courseName = showApplyDialog?.name ?: "",
                onDismiss = { showApplyDialog = null },
                onConfirm = { 
                    appliedCourseIds.value = appliedCourseIds.value + (showApplyDialog?.id ?: "")
                    showApplyDialog = null
                }
            )
        }

        if (showAddJobDialog) {
            AddListingDialog(
                onDismiss = { showAddJobDialog = false },
                onAdd = { newCourse ->
                    fullCourseList.add(0, newCourse)
                    showAddJobDialog = false
                }
            )
        }
        
        if (showProfileDialog) {
            ProfileDialog(
                name = userProfile?.name ?: "",
                role = userProfile?.role ?: UserRole.TRAINEE,
                onDismiss = { showProfileDialog = false }
            )
        }
    }
}

@Composable
fun MainDashboard(
    name: String, 
    role: UserRole,
    selectedCategory: String?,
    onCategorySelect: (String) -> Unit,
    courses: List<Course>,
    appliedIds: Set<String>,
    onApplyRequest: (Course) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            WelcomeBanner(name, role)
        }
        
        item {
            CategorySection(selectedCategory, onCategorySelect)
        }
        
        item {
            SectionHeader(
                if (role == UserRole.TRAINER || role == UserRole.JOB_PROVIDER) 
                    "Active Postings" else "Recommended Courses", 
                Icons.Default.Info
            )
        }
        
        if (courses.isEmpty()) {
            item {
                Text(
                    "No items found in this category.", 
                    modifier = Modifier.padding(24.dp), 
                    color = Color.Gray
                )
            }
        }

        items(courses) { course ->
            CourseCard(
                course = course, 
                role = role,
                isApplied = appliedIds.contains(course.id),
                onApplyClick = { onApplyRequest(course) }
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
            RoleSpecificSection(role)
        }
    }
}

@Composable
fun ExploreScreen(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    filterFreeOnly: Boolean,
    onFilterChange: (Boolean) -> Unit,
    results: List<Course>,
    appliedIds: Set<String>,
    role: UserRole,
    onApplyRequest: (Course) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Search programs, centers, or skills") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(12.dp)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            FilterChip(
                selected = filterFreeOnly,
                onClick = { onFilterChange(!filterFreeOnly) },
                label = { Text("Free Courses Only") },
                leadingIcon = if (filterFreeOnly) {
                    { Icon(Icons.Default.Check, contentDescription = null, Modifier.size(18.dp)) }
                } else null
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Found ${results.size} results",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(vertical = 12.dp)) {
            items(results) { course ->
                CourseCard(
                    course = course,
                    role = role,
                    isApplied = appliedIds.contains(course.id),
                    onApplyClick = { onApplyRequest(course) }
                )
            }
            if (results.isEmpty()) {
                item {
                    Text("No results match your search.", modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally), color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun ManagementScreen(role: UserRole, courses: List<Course>, appliedIds: Set<String>) {
    val isManagement = role == UserRole.TRAINER || role == UserRole.JOB_PROVIDER
    val itemsToShow = if (isManagement) {
        courses.take(3) // Mock showing "User's Own" courses
    } else {
        courses.filter { appliedIds.contains(it.id) }
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(
            text = if (isManagement) "Your Postings" else "My Applications",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        if (itemsToShow.isEmpty()) {
            Spacer(Modifier.height(40.dp))
            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally), tint = Color.LightGray)
            Text(
                "Nothing to show here yet.", 
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
                color = Color.Gray
            )
        } else {
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(itemsToShow) { course ->
                    CourseCard(course, role, appliedIds.contains(course.id), {})
                }
            }
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
fun CategorySection(selectedCategory: String?, onSelect: (String) -> Unit) {
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
                CategoryChip(label, icon, selectedCategory == label) { onSelect(label) }
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon, 
                contentDescription = null, 
                tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = label, 
            style = MaterialTheme.typography.labelMedium, 
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
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
    isApplied: Boolean,
    onApplyClick: () -> Unit
) {
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
                            if (isApplied) MaterialTheme.colorScheme.secondaryContainer 
                            else MaterialTheme.colorScheme.tertiaryContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isApplied) {
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
                        onClick = onApplyClick, 
                        enabled = !isApplied,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isApplied) Color.LightGray else MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(if (isApplied) "Applied" else "Apply Now", fontWeight = FontWeight.Bold)
                    }
                } else {
                    Row {
                        IconButton(onClick = { /* View Applicants */ }) {
                            Icon(Icons.Default.AccountBox, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { /* Edit */ }) {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Gray)
                        }
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
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Apply for $courseName") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Please provide basic details to the training center.")
                OutlinedTextField(
                    value = phone, 
                    onValueChange = { phone = it; error = false }, 
                    label = { Text("Phone Number") },
                    isError = error && phone.isEmpty()
                )
                OutlinedTextField(
                    value = location, 
                    onValueChange = { location = it; error = false }, 
                    label = { Text("Current Village/Town") },
                    isError = error && location.isEmpty()
                )
                if (error) {
                    Text("All fields are mandatory", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
                }
            }
        },
        confirmButton = {
            Button(onClick = { 
                if (phone.isNotEmpty() && location.isNotEmpty()) {
                    onConfirm()
                } else {
                    error = true
                }
            }) {
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
    var district by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Coding") }
    var isFree by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Skill Listing") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name, 
                    onValueChange = { name = it; error = false }, 
                    label = { Text("Course/Job Name") },
                    isError = error && name.isEmpty()
                )
                OutlinedTextField(
                    value = duration, 
                    onValueChange = { duration = it; error = false }, 
                    label = { Text("Duration (e.g. 3 Months)") },
                    isError = error && duration.isEmpty()
                )
                OutlinedTextField(
                    value = district, 
                    onValueChange = { district = it; error = false }, 
                    label = { Text("District") },
                    isError = error && district.isEmpty()
                )
                
                Text("Category")
                val categoryOptions = listOf("Coding", "Sewing", "Agriculture", "Electric", "Plumbing", "Auto")
                var expandedCategory by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(onClick = { expandedCategory = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(category)
                    }
                    DropdownMenu(expanded = expandedCategory, onDismissRequest = { expandedCategory = false }) {
                        categoryOptions.forEach { option ->
                            DropdownMenuItem(text = { Text(option) }, onClick = { category = option; expandedCategory = false })
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isFree, onCheckedChange = { isFree = it })
                    Text("Free Training")
                }
                if (error) {
                    Text("All fields are mandatory", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotEmpty() && duration.isNotEmpty() && district.isNotEmpty()) {
                    onAdd(Course(
                        id = System.currentTimeMillis().toString(),
                        name = name,
                        duration = duration,
                        district = district,
                        category = category,
                        isFree = isFree,
                        centerName = "My Training Center",
                        availableSeats = 20
                    ))
                } else {
                    error = true
                }
            }) {
                Text("Create")
            }
        }
    )
}

@Composable
fun ProfileDialog(name: String, role: UserRole, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("User Profile") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(80.dp), tint = MaterialTheme.colorScheme.primary)
                Text(text = name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(text = role.name.replace("_", " "), color = Color.Gray)
                Spacer(Modifier.height(16.dp))
                Divider()
                Spacer(Modifier.height(16.dp))
                Text("District: Mandya", style = MaterialTheme.typography.bodyMedium)
                Text("Joined: May 2026", style = MaterialTheme.typography.bodyMedium)
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun RoleSpecificSection(role: UserRole) {
    val message = when(role) {
        UserRole.TRAINEE -> null
        UserRole.TRAINER -> Triple("3 New batches assigned to you.", MaterialTheme.colorScheme.tertiaryContainer, Icons.Default.Star)
        UserRole.JOB_SEEKER -> Triple("5 Jobs matching your profile found.", Color(0xFFFFF3E0), Icons.Default.Info)
        UserRole.JOB_PROVIDER -> Triple("Reach 500+ candidates instantly.", Color(0xFFF3E5F5), Icons.Default.Email)
    } ?: return
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = message.second)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(message.third, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = message.first, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}
