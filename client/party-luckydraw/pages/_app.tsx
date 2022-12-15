import "../styles/globals.css";
import type { AppProps } from "next/app";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <div className="bg-[#E8C4C4] text-[#2B3A55] min-h-screen w-screen absolute ">
      <Component {...pageProps} />
    </div>
  );
}
