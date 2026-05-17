const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.sendApplicationNotification = functions.firestore
    .document("applications/{appId}")
    .onCreate(async (snapshot, context) => {
        const appData = snapshot.data();
        const userId = appData.userId;

        const userDoc = await admin.firestore().collection("users").document(userId).get();
        const userData = userDoc.data();

        if (userData && userData.fcmToken) {
            const message = {
                notification: {
                    title: "Application Submitted",
                    body: "Your application for the course has been received successfully!",
                },
                token: userData.fcmToken,
            };

            return admin.messaging().send(message);
        }
        return null;
    });
