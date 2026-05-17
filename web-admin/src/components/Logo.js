import React from 'react';

const Logo = () => {
  return (
    <div className="flex items-center space-x-2">
      <div className="w-10 h-10 bg-green-700 rounded-lg flex items-center justify-center">
        <span className="text-white font-bold text-xl">NS</span>
      </div>
      <span className="text-2xl font-bold text-gray-800">Namma Skill</span>
    </div>
  );
};

export default Logo;
