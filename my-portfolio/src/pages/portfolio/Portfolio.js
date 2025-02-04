import React, { useMemo, useState } from "react";
import data from "./data.json"; // Import JSON data
import Projects from "./projects/Projects";
import HeroSection from "./hero-section/HeroSection";
import Particles, { initParticlesEngine } from "@tsparticles/react";
import { loadSlim } from "@tsparticles/slim"; // Slim version for optimized build
import Skills from "./skills/Skills";
import Experience from "./experiences/Experience";

const Portfolio = () => {
  const [init, setInit] = React.useState(false);
  React.useEffect(() => {
    initParticlesEngine(async (engine) => {
      await loadSlim(engine);
    }).then(() => {
      setInit(true);
    });
  }, []);
  

  // const particlesOptions = useMemo(() => ({
  //   autoPlay: true,
  //   background: {
  //     color: { value: "#030314" },
  //   },
  //   particles: {
  //     number: { value: 150 },
  //     color: { value: ["#ff0080", "#ff6600", "#ffff00", "#00ffcc"] },
  //     shape: { type: "circle" },
  //     opacity: {
  //       value: 0.6,
  //       animation: { enable: true, speed: 0.5, sync: false },
  //     },
  //     size: {
  //       value: { min: 1, max: 5 },
  //       animation: { enable: true, speed: 2, sync: false },
  //     },
  //     move: {
  //       enable: true,
  //       speed: 1,
  //       direction: "random",
  //       outModes: { default: "out" },
  //     },
  //     twinkle: {
  //       particles: { enable: true, frequency: 0.1, opacity: 1 },
  //     },
  //   },
  //   interactivity: {
  //     events: {
  //       onHover: { enable: true, mode: "repulse" },
  //       onClick: { enable: true, mode: "push" },
  //     },
  //     modes: {
  //       repulse: { distance: 100, duration: 0.4 },
  //     },
  //   },
  // }), []);

 /* const particlesOptions = useMemo(() => ({
    background: { color: { value: "#000814" } },
    particles: {
      number: { value: 100 },
      color: { value: ["#0088ff", "#00ffcc", "#ff0080"] },
      shape: { type: "circle" },
      opacity: {
        value: 0.7,
        animation: { enable: true, speed: 1, sync: false },
      },
      size: {
        value: { min: 2, max: 6 },
        animation: { enable: true, speed: 1, sync: false },
      },
      move: {
        enable: true,
        speed: 0.5,
        direction: "top",
        random: true,
        outModes: { default: "out" },
        path: { enable: true, options: { type: "perlinNoise" } },
      },
    },
    interactivity: {
      events: { onHover: { enable: true, mode: "light" } },
      modes: { light: { area: { radius: 100 } } },
    },
  }), []);
  */

  // const particlesOptions = useMemo(() => ({
  //   background: { color: { value: "#0a001f" } },
  //   particles: {
  //     number: { value: 120 },
  //     color: { value: ["#ffcc00", "#ff00ff", "#00ffff"] },
  //     shape: { type: "square" },
  //     opacity: {
  //       value: { min: 0.2, max: 1 },
  //       animation: { enable: true, speed: 2, sync: false },
  //     },
  //     size: {
  //       value: { min: 2, max: 8 },
  //       animation: { enable: true, speed: 1.5, sync: false },
  //     },
  //     move: {
  //       enable: true,
  //       speed: 1,
  //       direction: "random",
  //       random: true,
  //       outModes: { default: "out" },
  //       path: { enable: true, options: { type: "chaotic" } },
  //     },
  //     twinkle: {
  //       particles: { enable: true, frequency: 0.3, opacity: 1 },
  //     },
  //   },
  //   interactivity: {
  //     events: { onClick: { enable: true, mode: "repulse" } },
  //   },
  // }), []);

 /* const particlesOptions = useMemo(() => ({
    background: { color: { value: "#000000" } },
    particles: {
      number: { value: 150 },
      color: { value: "#ffffff" },
      shape: { type: "circle" },
      opacity: {
        value: 1,
        animation: { enable: true, speed: 0.5, sync: false },
      },
      size: {
        value: { min: 1, max: 3 },
        animation: { enable: true, speed: 0.1, sync: false },
      },
      move: {
        enable: true,
        speed: 5,
        direction: "top",
        straight: true,
        outModes: { default: "out" },
      },
      warp: true,
    },
    interactivity: {
      events: {
        onHover: { enable: true, mode: "repulse" },
      },
    },
  }), []);
  */

  //data.skills.map(x=>{return {src:data.extras.icons[x].url,height:data.extras.icons[x].height,width:data.extras.icons[x].width};}
  const particlesOptions = useMemo(() => ({
    background: {
      color: { value: "#0D1117" }, // Dark background
    },
    particles: {
      number: { value: 100 }, // Number of floating icons
      shape: {
        type: "image", // Use image for particle shape
        options: {
          image: data.skills.map(x => {
            return {
              src: data.extras.icons[x].url,
              height: data.extras.icons[x].height,
              width: data.extras.icons[x].width,
            };
          }),
        },
      },
      size: {
        value: { min: 1, max: 10 }, // Random icon size
        animation: { enable: true, speed: 2, sync: false },
      },
      opacity: {
        value: 1, // Full opacity
      },
      move: {
        enable: true,
        speed: 0.5, // Speed of movement
        direction: "none",
        random: false, // Set to false to prevent random movement
        path: {
          enable: true,
          generator: "circle", // Strict circular path
          options: {
            radius: 200, // Radius of the circle
            angle: Math.PI / 30, // Control the speed of rotation (adjust to your preference)
          },
        },
        outModes: { default: "out" }, // Icons respawn when they go out of bounds
        straight: false,
      },
    },
    interactivity: {
      events: {
        onHover: { enable: true, mode: "bubble" }, // Hover makes icons grow
        onClick: { enable: true, mode: "push" }, // Click adds more icons
      },
    },
    detectRetina: false, // Ensures sharp images
  }), []);
  
  
  

  return (
    <section className="min-h-screen bg-gradient-to-br from-purple-500 via-blue-600 to-indigo-800 flex items-center justify-center p-6 relative">

      {/* Particle Effect */}
      <Particles
        id="tsparticles"
        className="absolute top-0 left-0 w-full h-full z-0"
        options={particlesOptions}
      />

      {/* Main Content */}
      <div className="relative z-10 flex flex-col items-center text-center w-full mark-transparent">
        {/* Hero Section */}
        <HeroSection />

        {/* Skills Section */}
        <Skills data={data} />

        {/* Projects Section */}
        <Projects data={data} />

        {/* Experience Section */}
        <Experience data={data} />
      </div>
    </section>
  );
};

export default Portfolio;
