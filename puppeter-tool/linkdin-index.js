import puppeteer from 'puppeteer';
import dotenv from 'dotenv';

// Load environment variables from .env file
dotenv.config();
// Script for posting on linkdin using Puppeteer
(async () => {
  // Launch the browser and open a new page
  const browser = await puppeteer.launch({ headless: true });
  const page = await browser.newPage();
  const startDate = new Date('2025-03-03'); // Starting date for scheduling posts
  const daysToAdd = 31; // Number of days to increment for posts
  page.setDefaultTimeout(1000 * 60); // Set default timeout to 1 minute

  // Navigate to the Buffer login page
  await page.goto('https://login.buffer.com/login?redirect=https://publish.buffer.com/channels/6794b913a31978b79c3b152a/calendar/month');

  // Enter email and password for login
  await page.type("input[id='email']", process.env.EMAIL); // Replace with your email
  await page.type("input[id='password']", process.env.PASSWORD); // Replace with your password

  // Click on the login button and wait for the page to load
  const loginButtonSelector = "button[id='login-form-submit']";
  await page.waitForSelector(loginButtonSelector);
  await page.click(loginButtonSelector);

  // Try to close any pop-up dialog if it appears
  try {
    const closeDialogSelector = "svg[data-icon='close']";
    await page.waitForSelector(closeDialogSelector, { timeout: 5000 });
    await page.click(closeDialogSelector);
    console.log("Closed dialog.");
  } catch (error) {
    console.log("No dialog to close.");
  }

  // Loop through the days to schedule posts
  for (let i = 0; i < daysToAdd; i++) {
    // Calculate the incremented date
    const incrementedDate = new Date(startDate);
    incrementedDate.setDate(startDate.getDate() + i);
    const selectDate = incrementedDate.toDateString();
    console.log(`Posting for: ${selectDate}`);

    // Attempt to click the "Create Post" button
    try {
      await new Promise(resolve => setTimeout(resolve, 1000 * 10)); // Wait for 10 seconds
      const newButtonSelector = "button.publish_base_GTmOA.publish_base_9SxrA.publish_large_D26h7.publish_secondary_-bRVa"; // Primary button selector
      await page.waitForSelector(newButtonSelector, { timeout: 5000 });
      await page.click(newButtonSelector);
      console.log("Clicked the primary 'Create Post' button successfully!");
    } catch (error) {
      console.log("Primary button not found. Trying fallback button...");
      const fallbackButtonSelector = "button[data-testid='queue-header-create-post']";
      await page.waitForSelector(fallbackButtonSelector, { timeout: 5000 });
      await page.click(fallbackButtonSelector);
      console.log("Clicked the fallback 'Create Post' button successfully!");
    }

    // Interact with the AI Assistant for generating a post
    const aiButtonSelector = "button[data-testid='ai-assistant-placeholder-button']";
    await page.waitForSelector(aiButtonSelector);
    await page.click(aiButtonSelector);

    const aiTextareaSelector = "textarea[data-testid='ai-assistant-textarea']";
    await page.waitForSelector(aiTextareaSelector);
    await page.type(aiTextareaSelector, 
      `### Prompt:  
"Generate a professional and engaging LinkedIn post targeted at developers, focusing on practical tips, tricks, or emerging topics that can help them in their daily work. The post should:  

1. Share a useful coding trick, productivity hack, or a tool recommendation for developers.  
2. Introduce a new or trending topic in software development (e.g., AI tools, frameworks, or best practices).  
3. Be concise, relatable, and actionable, with clear takeaways developers can apply immediately.  
4. Use a professional yet friendly tone, and include emojis to make it visually appealing.  
5. End with a call to action or question to encourage engagement, like: 'What’s your favorite trick that saves you time? 🤔'  

Ensure the length is suitable for a LinkedIn post and add hashtags relevant to developers and the topic."`
    );

    const aiGenerateButtonSelector = "button[data-testid='ai-assistant-generate-button']";
    await page.waitForSelector(aiGenerateButtonSelector);
    await page.click(aiGenerateButtonSelector);

    // Wait for and select the generated content
    await page.waitForSelector('.iJaygk');
    await page.click('.iJaygk');

    // Click on the "Schedule Post" option
    const moreButtonSelector = "div.style__ButtonSelect-bufferapp-ui__sc-2ithy7-0.cgTTmJ";
    await page.waitForSelector(moreButtonSelector);
    await page.click(moreButtonSelector);

    const schedulePostSelector = `p[title="Schedule Post"]`;
    await page.waitForSelector(schedulePostSelector);
    await page.click(schedulePostSelector);

    // Select the desired date for scheduling
    let dateFound = false;
    while (!dateFound) {
      try {
        const dateSelector = `div[aria-label="${selectDate}"]`;
        console.log(`Selecting date: ${selectDate}`);
        await page.waitForSelector(dateSelector, { timeout: 5000 });
        await page.click(dateSelector);
        dateFound = true;
      } catch (error) {
        console.log("Date not found, moving to the next month...");
        const nextMonthSelector = 
          "#composer-root > div.sc-hbvSAa.jckDMl > section.publish_section_X-Qjr.publish_clearfix_lkNAD > div > div.publish_stackedButtonsWrapper_uYG0o.publish_buttonsWrapper_bWZJO.schedule-post-button > div > div.sc-hDZrUb.dQXEgL > div > div:nth-child(1) > div > div > div:nth-child(1) > div:nth-child(2) > button";
        await page.waitForSelector(nextMonthSelector);
        await page.click(nextMonthSelector);
      }
    }

    // Confirm and finalize scheduling
    const scheduleButtonSelector = 
      `button.publish_base_GTmOA.publish_base_9SxrA.publish_large_D26h7.publish_primary_FQYGy.sc-fbFiXs.eTWMbb`;
    await page.waitForSelector(scheduleButtonSelector);
    await page.click(scheduleButtonSelector);
    console.log(`${selectDate} post scheduled successfully!`);
  }

  // Close the browser after completing all posts
  await browser.close();
  console.log("All posts scheduled successfully!");
})();
