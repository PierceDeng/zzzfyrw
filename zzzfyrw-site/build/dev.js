import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/


const dev_config = {

  server: {port:3005},
  test:"test",
  base:"/",
  plugins: [vue()]
}



export default ({ command, mode }) => {
  // console.log("========")
  // console.log(command,mode)
  // console.log("========")
  // if (command === 'serve') {
  //   return dev_config
  // } else {
  //   return {
  //     // build specific config
  //   }
  // }
  return dev_config;
}
