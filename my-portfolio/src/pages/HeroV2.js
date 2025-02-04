import React from "react";
import { motion } from "framer-motion";
import Particles, { initParticlesEngine } from "@tsparticles/react";
import { loadSlim } from "@tsparticles/slim"; // Slim version for optimized build
import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation

const HeroV2 = () => {
  const [init, setInit] = React.useState(false);
  const navigate = useNavigate(); // React Router's navigation hook

  React.useEffect(() => {
    initParticlesEngine(async (engine) => {
      await loadSlim(engine);
    }).then(() => {
      setInit(true);
    });
  }, []);

  const particlesLoaded = (container) => {
    console.log(container);
  };

  const options = React.useMemo(
    () => ({
      background: {
        color: {
          value: "#000000",
        },
      },
      fpsLimit: 120,
      interactivity: {
        events: {
          onClick: {
            enable: true,
            mode: "repulse",
          },
          onHover: {
            enable: true,
            mode: "grab",
          },
        },
        modes: {
          push: {
            distance: 200,
            duration: 15,
          },
          grab: {
            distance: 150,
          },
        },
      },
      particles: {
        color: {
          value: "#FDCB00",
        },
        links: {
          color: "#FFFFFF",
          distance: 150,
          enable: true,
          opacity: 0.4,
          width: 1,
        },
        move: {
          direction: "none",
          enable: true,
          outModes: {
            default: "bounce",
          },
          random: true,
          speed: 1,
          straight: false,
        },
        number: {
          density: {
            enable: true,
          },
          value: 150,
        },
        opacity: {
          value: 1.0,
        },
        shape: {
          type: "triangle",
        },
        size: {
          value: { min: 1, max: 5 },
        },
      },
      detectRetina: true,
    }),
    []
  );

  const handleNavigate = (navigateTo) => {
    navigate(navigateTo, {
      state: {
        transition: {
          type: "spring",
          stiffness: 75,
          damping: 15,
        },
      },
    });
  };

  return (
    <section className="hero min-h-screen flex items-center justify-center text-white relative bg-black">
      {/* Particles */}
      <Particles
        init={particlesLoaded}
        id="tsparticles"
        className="absolute top-0 left-0 w-full h-full z-0"
        options={options}
      />

      {/* Hero Content */}
      <div className="relative z-10 text-center">
        <motion.h1
          className="text-5xl md:text-6xl font-bold"
          initial={{ opacity: 0, y: -50 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
        >
          Hi, I'm <span className="text-yellow-400">Ravi Kumar</span>
        </motion.h1>
        <motion.p
          className="text-lg md:text-2xl font-light mt-5"
          initial={{ opacity: 0, x: -50 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 1, delay: 0.5 }}
        >
          Where Code Meets Creativity: A Full-Stack Journey Begins.
        </motion.p>
        <motion.div
          className="flex space-x-4 justify-center mt-6"
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 1, delay: 1 }}
        >
          <button
            onClick={()=>handleNavigate("/portfolio")}
            className="bg-yellow-400 text-gray-900 px-6 py-3 rounded-lg font-semibold hover:bg-yellow-500 transition"
          >
            View Portfolio
          </button>
          <button
            onClick={()=>handleNavigate("/contact")}
            className="bg-gray-800 text-white px-6 py-3 rounded-lg font-semibold hover:bg-gray-700 transition"
          >
            Contact Me
          </button>
        </motion.div>
      </div>
    </section>
  );
};

export default HeroV2;
