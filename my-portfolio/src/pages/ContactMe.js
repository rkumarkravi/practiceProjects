import React, { useMemo } from "react";
import { motion } from "framer-motion";
import { useNavigate } from "react-router-dom";
import Particles from "@tsparticles/react";
import { FaLinkedin, FaEnvelope, FaGithub } from "react-icons/fa";

const ContactMe = () => {
  const navigate = useNavigate();

  const handleNavigate = () => {
    navigate("/contact", {
      state: {
        transition: {
          type: "spring",
          stiffness: 75,
          damping: 10,
        },
      },
    });
  };

  const particlesOptions = useMemo(
    () => ({
      background: {
        color: {
          value: "#000000", // Background color
        },
      },
      fpsLimit: 120,
      interactivity: {
        events: {
          onClick: { enable: true, mode: "repulse" },
          onHover: { enable: true, mode: "grab" },
        },
        modes: {
          push: { quantity: 4 },
          repulse: { distance: 200, duration: 0.4 },
          grab: { distance: 150, links: { opacity: 0.5 } },
        },
      },
      particles: {
        color: { value: "#FDCB00" }, // Yellow particles
        links: {
          color: "#FFFFFF",
          distance: 150,
          enable: true,
          opacity: 0.4,
          width: 1,
        },
        move: {
          enable: true,
          speed: 2,
          direction: "none",
          outModes: { default: "bounce" },
        },
        number: { value: 100, density: { enable: true, area: 800 } },
        opacity: { value: 0.8 },
        shape: { type: "circle" },
        size: { value: { min: 1, max: 5 } },
      },
      detectRetina: true,
    }),
    []
  );

  return (
    <section className="min-h-screen bg-gradient-to-br from-purple-500 via-blue-600 to-indigo-800 flex items-center justify-center p-6 relative">
      {/* Particles Background */}
      <Particles
        id="tsparticles"
        className="absolute top-0 left-0 w-full h-full z-0"
        options={particlesOptions}
      />

      {/* Contact Form */}
      <motion.div
        initial={{ opacity: 0, y: 50 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        className="w-full max-w-3xl bg-white shadow-lg rounded-2xl p-8 space-y-6 relative z-10"
      >
        {/* Header Section */}
        <div className="text-center space-y-3">
          <motion.h1
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.2 }}
            className="text-3xl font-bold text-gray-800"
          >
            Get in Touch
          </motion.h1>
          <motion.p
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.4 }}
            className="text-lg text-gray-600"
          >
            I’d love to connect! Please fill out the form below or reach me via email or social media.
          </motion.p>
        </div>

        {/* Form Section */}
        <motion.form
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.8, delay: 0.6 }}
          action="https://formspree.io/f/{your_form_id}" // Replace with Formspree or custom backend
          method="POST"
          className="space-y-4"
        >
          <div>
            <label
              htmlFor="name"
              className="block text-sm font-medium text-gray-700"
            >
              Full Name
            </label>
            <input
              type="text"
              name="name"
              id="name"
              placeholder="Your Name"
              required
              className="mt-1 block w-full px-4 py-2 border rounded-lg shadow-sm focus:ring-yellow-400 focus:border-yellow-400 sm:text-sm"
            />
          </div>
          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium text-gray-700"
            >
              Email Address
            </label>
            <input
              type="email"
              name="email"
              id="email"
              placeholder="Your Email"
              required
              className="mt-1 block w-full px-4 py-2 border rounded-lg shadow-sm focus:ring-yellow-400 focus:border-yellow-400 sm:text-sm"
            />
          </div>
          <div>
            <label
              htmlFor="message"
              className="block text-sm font-medium text-gray-700"
            >
              Message
            </label>
            <textarea
              name="message"
              id="message"
              rows="4"
              placeholder="Write your message here..."
              required
              className="mt-1 block w-full px-4 py-2 border rounded-lg shadow-sm focus:ring-yellow-400 focus:border-yellow-400 sm:text-sm"
            ></textarea>
          </div>
          <div className="text-center">
            <motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              type="submit"
              className="w-full px-6 py-3 text-lg font-medium text-black bg-yellow-400 rounded-lg shadow-md hover:bg-yellow-500 focus:ring-2 focus:ring-yellow-400 focus:ring-offset-2 focus:outline-none"
            >
              Send Message
            </motion.button>
          </div>
        </motion.form>

        {/* Social Media Links */}
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8, delay: 0.8 }}
          className="text-center space-y-3"
        >
          <p className="text-gray-700">Or connect with me on:</p>
          <div className="flex justify-center space-x-6">
            <a
              href="https://www.linkedin.com/in/yourprofile"
              target="_blank"
              rel="noopener noreferrer"
              className="text-gray-400 hover:text-blue-500 text-2xl"
            >
              <FaLinkedin />
            </a>
            <a
              href="mailto:youremail@example.com"
              className="text-gray-400 hover:text-red-500 text-2xl"
            >
              <FaEnvelope />
            </a>
            <a
              href="https://github.com/yourprofile"
              target="_blank"
              rel="noopener noreferrer"
              className="text-gray-400 hover:text-black text-2xl"
            >
              <FaGithub />
            </a>
          </div>
        </motion.div>
      </motion.div>
    </section>
  );
};

export default ContactMe;
