import React from 'react'
import { motion } from "framer-motion";

function Experience({data}) {
  return (
    <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
          className="mt-12 w-full max-w-4xl h-screen"
        >
          <h2 className="text-3xl font-semibold text-blue-400">Experience</h2>
          {data.experience.map((job, index) => (
            <div key={index} className="mt-6 bg-gray-800 p-6 rounded-lg shadow-lg">
              <h3 className="text-2xl font-semibold">{job.role} at {job.company}</h3>
              <p className="text-gray-300">{job.duration}</p>
              <p className="text-gray-300">{job.description}</p>
            </div>
          ))}
        </motion.div>
  )
}

export default Experience