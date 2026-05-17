import React from 'react';
import Logo from '../components/Logo';

const Dashboard = () => {
  return (
    <div className="p-8 max-w-7xl mx-auto">
      <div className="flex justify-between items-center mb-10">
        <Logo />
        <div className="flex items-center space-x-4">
          <span className="text-gray-600">Admin User</span>
          <button className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition">Logout</button>
        </div>
      </div>

      <h1 className="text-3xl font-bold mb-6 text-gray-800">Operational Overview</h1>

      <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-10">
        <StatCard title="Total Applications" value="1,284" color="text-green-600" />
        <StatCard title="Certified Students" value="450" color="text-blue-600" />
        <StatCard title="Active Centers" value="18" color="text-yellow-600" />
        <StatCard title="Course Completion Rate" value="84%" color="text-purple-600" />
      </div>

      <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
        <h2 className="text-xl font-semibold mb-4">Recent Activity</h2>
        <p className="text-gray-500 italic text-center py-10">Integration with Firebase Firestore will show live data here.</p>
      </div>
    </div>
  );
};

const StatCard = ({ title, value, color }) => (
  <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
    <h2 className="text-sm font-medium text-gray-500 uppercase tracking-wider">{title}</h2>
    <p className={`text-3xl font-bold mt-2 ${color}`}>{value}</p>
  </div>
);

export default Dashboard;
