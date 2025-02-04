import React from 'react'
import { motion } from "framer-motion";

function Projects({data}) {
  return (
    <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
          className="mt-12 w-full max-w-4xl h-screen"
        >
          <h2 className="text-3xl font-semibold text-blue-400">Projects</h2>
          {data.projects.map((project, index) => (
            <div key={index} className="mt-6 bg-gray-800 p-6 rounded-lg shadow-lg">
              <h3 className="text-2xl font-semibold">{project.title}</h3>
              <p className="text-gray-300">{project.description}</p>
              <a
                href={project.link}
                target="_blank"
                rel="noopener noreferrer"
                className="text-blue-400 hover:underline"
              >
                View Project
              </a>
            </div>
          ))}
        </motion.div>
  )
}

export default Projects