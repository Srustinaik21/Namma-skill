import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";
import { getAuth } from "firebase/auth";

// Replace these with your actual Firebase project configuration
const firebaseConfig = {
  apiKey: "AIzaSy-DEMO-WEB-KEY",
  authDomain: "namma-skill-demo.firebaseapp.com",
  projectId: "namma-skill-demo",
  storageBucket: "namma-skill-demo.appspot.com",
  messagingSenderId: "1234567890",
  appId: "1:1234567890:web:abcdef123456"
};

const app = initializeApp(firebaseConfig);
export const db = getFirestore(app);
export const auth = getAuth(app);
export default app;
