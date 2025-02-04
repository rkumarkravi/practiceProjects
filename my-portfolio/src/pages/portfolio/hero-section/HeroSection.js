import React, { useState, useEffect } from 'react';
import { motion } from "framer-motion";
import { FaReact, FaNodeJs, FaDatabase, FaHtml5, FaCss3Alt, FaJsSquare } from "react-icons/fa";
import data from "../data.json"; // Import data.json file

function HeroSection() {
  const [resumeLink, setResumeLink] = useState("/path/to/resume.pdf");
  const [experience, setExperience] = useState("");

  useEffect(() => {
    if (data.basics && data.basics.startYear) {
      setExperience(calculateDifference(data.basics.startYear));
    }
  }, []);

  const calculateDifference = (fromDate, endDate = null) => {
    var startDate = new Date(fromDate);
    var currentDate = new Date();
    if (endDate) {
      currentDate = new Date(endDate);
    }
    var yearsDiff = currentDate.getFullYear() - startDate.getFullYear();
    var monthsDiff = currentDate.getMonth() - startDate.getMonth();
    if (monthsDiff < 0) {
      yearsDiff--;
      monthsDiff += 12;
    }
    return yearsDiff + (yearsDiff === 1 ? " yr " : " yrs ") + monthsDiff + " mo";
  };

  return (
    <div className="hero w-full h-screen flex flex-col justify-center items-center text-white text-center relative overflow-hidden">
      <motion.div 
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 1 }}
        className="text-6xl md:text-7xl font-extrabold text-yellow-400 shadow-lg drop-shadow-lg"
      >
        {data.basics.name}
      </motion.div>
      <motion.p
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 1.2 }}
        className="mt-4 text-2xl text-gray-300 font-medium"
      >
        {data.current.currentRole} @ {data.current.companyName}
      </motion.p>

      <motion.p
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 1.4 }}
        className="text-lg text-gray-400 mt-2"
      >
        Total Experience: {experience}
      </motion.p>

      {/* Skills Icons */}
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ duration: 1.6 }}
        className="flex space-x-6 mt-6 text-4xl"
      >
        <FaHtml5 className="text-orange-500 transition transform hover:scale-110" />
        <FaCss3Alt className="text-blue-500 transition transform hover:scale-110" />
        <FaJsSquare className="text-yellow-400 transition transform hover:scale-110" />
        <FaReact className="text-blue-400 transition transform hover:scale-110 animate-spin" />
        <FaNodeJs className="text-green-500 transition transform hover:scale-110" />
        <FaDatabase className="text-gray-500 transition transform hover:scale-110" />
      </motion.div>

      {/* Download Resume Button */}
      <motion.a
        href={resumeLink}
        download
        initial={{ scale: 0.8 }}
        animate={{ scale: 1 }}
        transition={{ duration: 0.5 }}
        className="mt-6 px-8 py-3 bg-yellow-400 text-black font-semibold rounded-lg shadow-xl transition duration-300 hover:bg-yellow-500 transform hover:scale-105"
      >
        Download Resume
      </motion.a>
    </div>
  );
}

export default HeroSection;