package com.nammaskill.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaskill.domain.model.UserRole
import com.nammaskill.ui.theme.TraineeColor
import com.nammaskill.ui.theme.TrainerColor
import com.nammaskill.ui.theme.JobSeekerColor
import com.nammaskill.ui.theme.JobProviderColor

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.TRAINEE) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Namma Skill",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Empowering Rural Karnataka",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Your Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "I am a...",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        RoleGrid(selectedRole) { selectedRole = it }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Button(
            onClick = {
                if (name.isNotEmpty()) {
                    viewModel.login(name, selectedRole)
                    onLoginSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Get Started", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun RoleGrid(selectedRole: UserRole, onRoleSelect: (UserRole) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            RoleCard(UserRole.TRAINEE, "Trainee", TraineeColor, selectedRole == UserRole.TRAINEE, Modifier.weight(1f)) { onRoleSelect(it) }
            RoleCard(UserRole.TRAINER, "Trainer", TrainerColor, selectedRole == UserRole.TRAINER, Modifier.weight(1f)) { onRoleSelect(it) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            RoleCard(UserRole.JOB_SEEKER, "Job Seeker", JobSeekerColor, selectedRole == UserRole.JOB_SEEKER, Modifier.weight(1f)) { onRoleSelect(it) }
            RoleCard(UserRole.JOB_PROVIDER, "Provider", JobProviderColor, selectedRole == UserRole.JOB_PROVIDER, Modifier.weight(1f)) { onRoleSelect(it) }
        }
    }
}

@Composable
fun RoleCard(
    role: UserRole, 
    label: String, 
    color: Color, 
    isSelected: Boolean, 
    modifier: Modifier,
    onClick: (UserRole) -> Unit
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) color else color.copy(alpha = 0.15f))
            .clickable { onClick(role) }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else color
        )
    }
}
