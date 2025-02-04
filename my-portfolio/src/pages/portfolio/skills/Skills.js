import React from 'react';
import { motion } from 'framer-motion';

function Skills({ data }) {
    return (
        <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 1 }}
            className="mt-12 w-full max-w-4xl h-screen"
        >
            <h2 className="text-6xl font-bold text-yellow-400">Skills</h2>
            <div className="relative mt-6">
                {/* Grid container with horizontal scroll */}
                <div className="overflow-x-auto">
                    {/* Apply grid layout */}
                    <motion.div
                        className="grid grid-cols-5 gap-4 py-4"
                        style={{ minWidth: '100%', whiteSpace: 'nowrap' }}
                    >
                        {data.skills.map((skill, index) => (
                            <div
                                key={index}
                                className="flex flex-col items-center justify-center bg-gray-800 rounded-lg p-4"
                            >
                                <img
                                    src={data.extras.icons[skill].url}
                                    height={data.extras.icons[skill].height}
                                    width={data.extras.icons[skill].width}
                                    alt={skill}
                                    className="mb-2"
                                />
                                <span className="text-yellow-400">{data.extras.icons[skill].name}</span>
                            </div>
                        ))}
                    </motion.div>
                </div>
            </div>
        </motion.div>
    );
}

export default Skills;
